package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.entity.Drink;
import by.pvt.shaurma.core.repository.DrinkDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrinkDaoRepository implements DrinkDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public DrinkDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }

    @Override
    public List<Drink> getAllDrinks() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select d from Drink d", Drink.class).getResultList();
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
        session.merge(drink);
        session.getTransaction().commit();
        session.close();
    }
}
