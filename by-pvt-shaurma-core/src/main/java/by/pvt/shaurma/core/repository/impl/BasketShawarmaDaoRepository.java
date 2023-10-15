package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.BasketShawarma;
import by.pvt.shaurma.core.repository.BasketShawarmaDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;

public class BasketShawarmaDaoRepository implements BasketShawarmaDao {
    private final SessionFactory sessionFactory;

    public BasketShawarmaDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }


    @Override
    public List<BasketShawarma> getAllBaskets() {
        Session session = sessionFactory.openSession();
        List<BasketShawarma> goods = session.createQuery("select b from BasketShawarma b").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void add(BasketShawarma basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(basket);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long orderId, Long shawarmaId) {
        Session session = sessionFactory.openSession();
        BasketShawarma basket =  session.createQuery("select s from BasketShawarma s where s.id.orderId=:orderId and s.id.shawarmaId=:shawarmaId", BasketShawarma.class).setParameter("orderId", orderId).setParameter("shawarmaId", shawarmaId).getSingleResult();
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
        session.close();
    }

    public BigDecimal totalPriceShawarma(Long orderId) {
        Session session = sessionFactory.openSession();
        BigDecimal price = session.createQuery("select sum(d.cost) from BasketShawarma d where d.id.orderId=:orderId", BigDecimal.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return price;
    }

    public Long totalCountShawarma(Long orderId) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select sum(d.count) from BasketShawarma d where d.id.orderId=:orderId", Long.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return count;
    }
}
