package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Good;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.repository.OrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    public void update(Order order) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Order> findOrderByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery("select o from Order o where userId=:userId", Order.class).setParameter("userId", userId).getResultList();
        session.close();
        return orders;
    }

    public void addGoodInOrder(Long goodId, Long orderId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Good good = session.get(Good.class, goodId);
        Order order = session.get(Order.class, orderId);
        List<Good> goods = order.getGoods();
        goods.add(good);
        session.merge(order);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteGoodInOrder(Long goodId, Long orderId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Good good = session.get(Good.class, goodId);
        Order order = session.get(Order.class, orderId);
        List<Good> goods = order.getGoods();
        goods.remove(good);
        session.merge(order);
        session.getTransaction().commit();
        session.close();
    }

    public List<Order> findAllOrdersWhereCostGreaterThan(BigDecimal cost) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        criteriaQuery.select(order).where(criteriaBuilder.gt(order.get("cost"), cost)).orderBy(criteriaBuilder.asc(order.get("cost")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public BigDecimal getSumCostOfGoodsInOrder() {
        EntityManager entityManager = sessionFactory.createEntityManager();
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
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> order = criteriaQuery.from(Order.class);
        Join<Order, Client> client = order.join("userId");
        criteriaQuery.select(order).where(criteriaBuilder.and(criteriaBuilder.equal(client.get("name"), name), criteriaBuilder.equal(client.get("login"), login)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
