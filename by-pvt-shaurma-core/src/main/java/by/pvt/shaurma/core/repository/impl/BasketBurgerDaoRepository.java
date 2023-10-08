package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.BasketBurger;
import by.pvt.shaurma.core.repository.BasketBurgerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BasketBurgerDaoRepository implements BasketBurgerDao {
    private final SessionFactory sessionFactory;

    public BasketBurgerDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }


    @Override
    public List<BasketBurger> getAllBaskets() {
        Session session = sessionFactory.openSession();
        List<BasketBurger> goods = session.createQuery("select b from BasketBurger b").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void add(BasketBurger basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(basket);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateBasket(BasketBurger basket) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(basket);
        session.getTransaction().commit();
    }

    @Override
    public BasketBurger getBasketById(Long id) {
        Session session = sessionFactory.openSession();
        BasketBurger basket = session.get(BasketBurger.class, id);
        return basket;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        BasketBurger basket = session.get(BasketBurger.class, id);
        session.getTransaction().begin();
        session.remove(basket);
        session.getTransaction().commit();
    }
}
