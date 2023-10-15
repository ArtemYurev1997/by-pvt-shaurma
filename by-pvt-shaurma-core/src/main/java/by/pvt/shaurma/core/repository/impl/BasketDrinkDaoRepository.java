package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.repository.BasketDrinkDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;

public class BasketDrinkDaoRepository implements BasketDrinkDao {
    private final SessionFactory sessionFactory;

    public BasketDrinkDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }


    @Override
    public List<BasketDrink> getAllBaskets() {
        Session session = sessionFactory.openSession();
        List<BasketDrink> goods = session.createQuery("select b from BasketDrink b").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void add(BasketDrink basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(basket);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long orderId, Long drinkId) {
        Session session = sessionFactory.openSession();
        BasketDrink basket = (BasketDrink) session.createQuery("select d from BasketDrink d where d.id.orderId=:orderId and d.id.drinkId=:drinkId").setParameter("orderId", orderId).setParameter("drinkId", drinkId).getSingleResult();
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
        session.close();
    }

    public BigDecimal totalPriceDrink(Long orderId) {
        Session session = sessionFactory.openSession();
        BigDecimal price = session.createQuery("select sum(d.cost) from BasketDrink d where d.id.orderId=:orderId", BigDecimal.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return price;
    }

    public Long totalCountDrink(Long orderId) {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select sum(d.count) from BasketDrink d where d.id.orderId=:orderId", Long.class).setParameter("orderId", orderId).getSingleResult();
        session.close();
        return count;
    }
}
