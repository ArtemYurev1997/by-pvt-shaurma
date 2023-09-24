package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.api.dto.OrderRequest;
import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.repository.OrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDaoRepository implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("select o from Order o").getResultList();
        session.close();
        return orders;
    }

    @Override
    public Order getOrderById(Long id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        return order;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.getTransaction().begin();
        session.remove(order);
        session.getTransaction().commit();
    }

    @Override
    public void addOrder(Order order) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Long id, OrderRequest orderRequest) {
        Session session = sessionFactory.openSession();
        Order order = session.get(Order.class, id);
        session.getTransaction().begin();
        session.update(order);
        session.getTransaction().commit();
    }

    @Override
    public List<Order> findOrderByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("select o from Order o where o.userId=?1", Order.class).getResultList();
        session.close();
        return orders;
    }
}
