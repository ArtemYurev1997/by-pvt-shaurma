package by.pvt.shaurma.core.repository.impl;


import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Good;
import by.pvt.shaurma.core.repository.GoodDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GoodDaoRepository implements GoodDao {
    private final SessionFactory sessionFactory;

    public GoodDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Good> getAllGoods() {
        Session session = sessionFactory.openSession();
        List<Good> goods = session.createQuery("select g from Good g").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void deleteGood(Long id) {
        Session session = sessionFactory.openSession();
        Good good = session.get(Good.class, id);
        session.getTransaction().begin();
        session.remove(good);
        session.getTransaction().commit();
    }

    @Override
    public void addGood(Good good) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(good);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Good getGoodById(Long id) {
        Session session = sessionFactory.openSession();
        Good good = session.get(Good.class, id);
        return good;
    }

    @Override
    public void update(Good newGood) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(newGood);
        session.getTransaction().commit();
        session.close();
    }
}
