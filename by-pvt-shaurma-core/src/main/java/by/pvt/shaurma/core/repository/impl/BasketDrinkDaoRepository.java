package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.BasketDrink;
import by.pvt.shaurma.core.repository.BasketDrinkDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    public void updateBasket(BasketDrink basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(basket);
        session.getTransaction().commit();
    }

    @Override
    public BasketDrink getBasketById(Long id) {
        Session session = sessionFactory.openSession();
        BasketDrink basket = session.get(BasketDrink.class, id);
        return basket;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        BasketDrink basket = session.get(BasketDrink.class, id);
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
    }
}
