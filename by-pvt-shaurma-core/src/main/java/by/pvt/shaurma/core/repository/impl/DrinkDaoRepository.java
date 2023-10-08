package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Drink;
import by.pvt.shaurma.core.entity.Shawarma;
import by.pvt.shaurma.core.repository.DrinkDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DrinkDaoRepository implements DrinkDao {
    private final SessionFactory sessionFactory;

    public DrinkDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Drink> getAllDrinks() {
        Session session = sessionFactory.openSession();
        List<Drink> drinks = session.createQuery("select d from Drink d").getResultList();
        session.close();
        return drinks;
    }

    @Override
    public void deleteDrink(Long id) {
        Session session = sessionFactory.openSession();
        Drink drink = session.get(Drink.class, id);
        session.getTransaction().begin();
        session.remove(drink);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addDrink(Drink drink) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(drink);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Drink getDrinkById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Drink.class, id);
    }

    @Override
    public void update(Drink drink) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(drink);
        session.getTransaction().commit();
        session.close();
    }
}
