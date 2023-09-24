package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Basket;
import by.pvt.shaurma.core.repository.BasketDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BasketDaoRepository implements BasketDao {
    private final SessionFactory sessionFactory;

    public BasketDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Basket> getAllBaskets() {
        Session session = sessionFactory.openSession();
        List<Basket> goods = session.createQuery("select b from Basket b").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void add(Basket basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(basket);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBasket(Basket basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(basket);
        session.getTransaction().commit();
    }

    @Override
    public Basket getBasketById(Long id) {
        Session session = sessionFactory.openSession();
        Basket basket = session.get(Basket.class, id);
        return basket;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Basket basket = session.get(Basket.class, id);
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
    }
}
