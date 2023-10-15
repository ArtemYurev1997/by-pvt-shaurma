package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Burger;
import by.pvt.shaurma.core.repository.BurgerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BurgerDaoRepository implements BurgerDao {
    private final SessionFactory sessionFactory;

    public BurgerDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Burger> getAllBurgers() {
        Session session = sessionFactory.openSession();
        List<Burger> burgers = session.createQuery("select b from Burger b").getResultList();
        session.close();
        return burgers;
    }

    @Override
    public void deleteBurger(Long id) {
        Session session = sessionFactory.openSession();
        Burger burger = session.get(Burger.class, id);
        session.getTransaction().begin();
        session.remove(burger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addBurger(Burger burger) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(burger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Burger getBurgerById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Burger.class, id);
    }

    @Override
    public void update(Burger burger) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(burger);
        session.getTransaction().commit();
        session.close();
    }
}
