package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Ingridient;
import by.pvt.shaurma.core.repository.IngridientDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class IngridientDaoRepository implements IngridientDao {
    private final SessionFactory sessionFactory;

    public IngridientDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public List<Ingridient> getAllIngridients() {
        Session session = sessionFactory.openSession();
        List<Ingridient> goods = session.createQuery("select i from Ingridient i").getResultList();
        session.close();
        return goods;
    }

    @Override
    public void deleteIngridient(Long id) {
        Session session = sessionFactory.openSession();
        Ingridient ingridient = session.get(Ingridient.class, id);
        session.getTransaction().begin();
        session.remove(ingridient);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addIngridient(Ingridient ingridient) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(ingridient);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Ingridient getIngridientById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Ingridient.class, id);
    }

    @Override
    public void update(Ingridient ingridient) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(ingridient);
        session.getTransaction().commit();
        session.close();
    }
}
