package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.*;
import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.exception.PaymentException;
import by.pvt.shaurma.core.exception.TransactionException;
import by.pvt.shaurma.core.mapper.BurgerMapper;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.mapper.ShawarmaMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import by.pvt.shaurma.core.repository.OrderDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderApi {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;


    public OrderService(OrderDao orderDao, OrderMapper orderMapper, ClientDao clientDao, ClientMapper clientMapper, SessionFactory sessionFactory) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        return orderMapper.mapToOrderResponse(orderDao.addOrder(orderMapper.toEntity(orderRequest)));
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public OrderResponse findById(Long id) {
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(id));
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderDao.getAllOrders().stream().map(orderMapper::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order;
        order = new Order(null, new Client(), 0L, new BigDecimal(0), null, "Не оформлен", "Не оплачено");
        orderDao.addOrder(order);
        return orderMapper.mapToOrderResponse(order);
    }

    public OrderResponse updateOrderToClient(Long userId, Long orderId) {
        Client client = clientDao.getClientById(userId);
        Order order = orderDao.getOrderById(orderId);
        order.setUserId(client);
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderId));
    }

    @Override
    public OrderResponse checkOut(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        Client user = order.getUserId();
        Comment comment = new Comment(null, " ", LocalDateTime.now());
        order.setDate(LocalDate.now());
        order.setTelephone(user.getTelephone());
        order.setAddress(user.getAddress());
        order.setComment(comment.getComment());
        order.setPayment("Заказ оплачен!");
        order.setStatus("Заказ подтвержден!");
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }

//установить время жизни тразакции
    public OrderResponse payment(BigDecimal sum, Long orderId, Long userId) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            BigDecimal price = session.createQuery("select o.cost from Order o where o.id=:orderId and o.userId.id=:userId", BigDecimal.class).setParameter("orderId", orderId).setParameter("userId", userId).getSingleResult();
            int result = sum.compareTo(price);
            if (result == 0) {
                System.out.println("Оплата прошла успешно! Заказ Оформлен!");
                return checkOut(orderId);
            }
            else {
              throw new PaymentException("Введите нужную сумму!");
            }
        }
        catch (HibernateException e) {
            transaction.rollback();
            throw new TransactionException("Транзакция отменена!");
        }
        finally{
            session.close();
        }
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        return orderDao.findOrderByUserId(userId).stream().map(orderMapper::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse changeStatus(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        order.setStatus("Оформлен");
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }

    public OrderResponse getOrderById(Long id) {
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(id));
    }

    public List<ShawarmaDto> getShawarmaDtoByIngridient(String name) {
        ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
        session = sessionFactory.openSession();
        List<Shawarma> shawarmas = session.createQuery("select s from Shawarma s join s.ingridients i where i.name=:name", Shawarma.class).setParameter("name", name).getResultList();
        return shawarmas.stream().map(shawarmaMapper::toShawarmaDto).collect(Collectors.toList());
    }

    @Override
    public List<BurgerDto> getBurgersDtoByIngridient(String name) {
        BurgerMapper burgerMapper = new BurgerMapper();
        session = sessionFactory.openSession();
        List<Burger> burgers = session.createQuery("select b from Burger s join b.ingridients i where i.name=:name", Burger.class).setParameter("name", name).getResultList();
        return burgers.stream().map(burgerMapper::toBurgerDto).collect(Collectors.toList());
    }

    @Override
    public CommentResponse createCommentByClient(Long clientId, CommentRequest commentRequest) {
        return null;
    }

    public ShawarmaDto createShawarma(Long id, Long start, Long end, String type, Long code) {
        ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Shawarma shawarma = new Shawarma(type, code, new BigDecimal(0));
        List<Ingridient> ingridients = session.createQuery("select i from Ingridient i where i.id>=:start and i.id<=:end", Ingridient.class).setParameter("start", start).setParameter("end", end).getResultList();
        int query = session.createQuery("update Ingridient i set i.total=i.total-:decrement where i.id>=:start and i.id<=:end", Ingridient.class).setParameter("decrement", 1L).setParameter("start", start).setParameter("end", end).executeUpdate();
        BigDecimal price = session.createQuery("select sum(i.price) from Ingridient i where i.id>=:start and i.id<=:end", BigDecimal.class).setParameter("start", start).setParameter("end", end).getSingleResult();
        shawarma.setPrice(price);
        shawarma.setIngridients(ingridients);
        session.persist(shawarma);
        session.getTransaction().commit();
        session.close();
        return shawarmaMapper.toShawarmaDto(shawarma);
    }

    public List<Order> findAllOrdersWhereCostGreaterThan(BigDecimal cost) {
        EntityManager entityManager = (EntityManager) sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        criteriaQuery.select(order).where(criteriaBuilder.gt(order.get("cost"), cost)).orderBy(criteriaBuilder.asc(order.get("cost")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public BigDecimal getSumCostOfGoodsInOrder() {
        EntityManager entityManager =  sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        criteriaQuery.select(criteriaBuilder.sum(criteriaQuery.from(Order.class).get("cost")));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<Order> findAllOrdersByRangeDate(LocalDate min, LocalDate max) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        criteriaQuery.select(order).where(criteriaBuilder.between(order.get("date"), min, max)).orderBy(criteriaBuilder.asc(order.get("date")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Order> findNameAndLoginClients(String name, String login) {
        EntityManager entityManager =  sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        Join<Order, Client> client = order.join("userId");
        criteriaQuery.select(order).where(criteriaBuilder.and(criteriaBuilder.equal(client.get("name"), name), criteriaBuilder.equal(client.get("login"), login)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
