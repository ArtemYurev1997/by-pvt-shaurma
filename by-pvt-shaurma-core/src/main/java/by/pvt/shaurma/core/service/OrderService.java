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
import org.springframework.data.domain.Page;
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
    public Page<OrderResponse> getOrdersPages(int page, int size) {
        return null;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order;
        order = new Order(null, new Client(), 0L, new BigDecimal(0), null, "Не оформлен", "Не оплачено");
        orderDao.addOrder(order);
        return orderMapper.mapToOrderResponse(order);
    }

    public OrderResponse updateOrderToClient(OrderRequest orderRequest) {
        Client client = clientDao.getClientById(orderRequest.getId());
        Order order = orderDao.getOrderById(orderRequest.getUserId().getId());
        order.setUserId(client);
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(orderRequest.getId()));
    }

    @Override
    public OrderResponse checkOut(OrderRequest orderRequest) {
        Order order = orderDao.getOrderById(orderRequest.getId());
        Client user = order.getUserId();
        Comment comment = new Comment(null, " ", LocalDate.now(), user);
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
    public OrderResponse payment(OrderRequest orderRequest) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Long orderId = orderRequest.getId();
            Long userId = orderRequest.getUserId().getId();
            BigDecimal sum = orderRequest.getCost();
            BigDecimal price = session.createQuery("select o.cost from Order o where o.id=:orderId and o.userId.id=:userId", BigDecimal.class).setParameter("orderId", orderId).setParameter("userId", userId).getSingleResult();
            int result = sum.compareTo(price);
            if (result == 0) {
                System.out.println("Оплата прошла успешно! Заказ Оформлен!");
                return checkOut(orderRequest);
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
    public OrderResponse changeStatus(OrderRequest orderRequest) {
        Long orderId = orderRequest.getId();
        Order order = orderDao.getOrderById(orderId);
        order.setStatus("Оформлен");
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }

    @Override
    public OrderResponse addCostAndCountToOrder(OrderRequest orderRequest) {
        return null;
    }

    public OrderResponse getOrderById(Long id) {
        return orderMapper.mapToOrderResponse(orderDao.getOrderById(id));
    }


    @Override
    public CommentResponse createCommentByClient( CommentRequest commentRequest) {
        return null;
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
