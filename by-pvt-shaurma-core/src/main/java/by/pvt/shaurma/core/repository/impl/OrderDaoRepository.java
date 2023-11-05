package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.entity.*;
import by.pvt.shaurma.core.repository.OrderDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDaoRepository implements OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public OrderDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
    public Order getOrderById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Order.class, id);
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
    public Order addOrder(Order order) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(order);
        session.getTransaction().commit();
        session.close();
        return order;
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
         return session.createQuery("select o from Order o where o.userId=:userId", Order.class).setParameter("userId", userId).getResultList();
    }


    public void createShawarma(Long id, Long start, Long end) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Shawarma shawarma= session.get(Shawarma.class, id);
        List<Ingridient> ingridients = session.createQuery("select i from Ingridient i where i.id>=:start and i.id<=:end", Ingridient.class).setParameter("start", start).setParameter("end", end).getResultList();
        int query = session.createQuery("update Ingridient i set i.total=i.total-:decrement where i.id>=:start and i.id<=:end", Integer.class).setParameter("decrement", 1L).setParameter("start", start).setParameter("end", end).executeUpdate();
        BigDecimal price = (BigDecimal) session.createQuery("select sum(i.price) from Ingridient i where i.id>=:start and i.id<=:end").setParameter("start", start).setParameter("end", end).getSingleResult();
        shawarma.setPrice(price);
        shawarma.setIngridients(ingridients);
        session.update(shawarma);
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
