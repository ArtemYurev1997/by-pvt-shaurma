package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.OrderApi;
import by.pvt.shaurma.api.dto.OrderResponse;
import by.pvt.shaurma.api.dto.ShawarmaDto;
import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Comment;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.entity.Shawarma;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.mapper.OrderMapper;
import by.pvt.shaurma.core.mapper.ShawarmaMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import by.pvt.shaurma.core.repository.OrderDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderApi {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;


    public OrderService(OrderDao orderDao, OrderMapper orderMapper, ClientDao clientDao, ClientMapper clientMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public OrderResponse createOrder() {
        Order order;
        order = new Order(null, null, 0L, new BigDecimal(0), null, "Не оформлен", "Не оплачено");
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
        Comment comment = new Comment(null, " ", LocalDate.now());
        order.setDate(LocalDate.now());
        order.setTelephone(user.getTelephone());
        order.setAddress(user.getAddress());
        order.setComment(comment.getComment());
        order.setPayment("Заказ оплачен!");
        order.setStatus("Заказ подтвержден!");
        orderDao.update(order);
        return orderMapper.mapToOrderResponse(order);
    }

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
                System.out.println("Оплата отменена!");
                return null;
            }
        }
        catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Tранзакция отменена!");
            return null;
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

    public List<ShawarmaDto> getShawarmaDtoForIngridient(String name) {
        ShawarmaMapper shawarmaMapper = new ShawarmaMapper();
        session = sessionFactory.openSession();
        List<Shawarma> shawarmas = session.createQuery("select s from Shawarma s join s.ingridients i where i.name=:name").setParameter("name", name).getResultList();
        return shawarmas.stream().map(shawarmaMapper::toShawarmaDto).collect(Collectors.toList());
    }



}
