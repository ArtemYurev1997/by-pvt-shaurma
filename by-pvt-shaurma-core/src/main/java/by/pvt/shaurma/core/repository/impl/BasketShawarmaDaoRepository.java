package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.BasketShawarma;
import by.pvt.shaurma.core.repository.BasketShawarmaDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    public void updateBasket(BasketShawarma basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(basket);
        session.getTransaction().commit();
    }

    @Override
    public BasketShawarma getBasketById(Long id) {
        Session session = sessionFactory.openSession();
        BasketShawarma basket = session.get(BasketShawarma.class, id);
        return basket;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        BasketShawarma basket = session.get(BasketShawarma.class, id);
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
    }
}
