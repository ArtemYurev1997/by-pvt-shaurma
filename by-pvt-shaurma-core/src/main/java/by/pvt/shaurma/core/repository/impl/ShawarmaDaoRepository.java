package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.entity.Shawarma;
import by.pvt.shaurma.core.repository.ShawarmaDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShawarmaDaoRepository implements ShawarmaDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public ShawarmaDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }

    @Override
    public List<Shawarma> getAllShawarmas() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select s from Shawarma s", Shawarma.class).getResultList();
    }

    @Override
    public void deleteShawarma(Long id) {
        Session session = sessionFactory.openSession();
        Shawarma shawarma = session.get(Shawarma.class, id);
        session.getTransaction().begin();
        session.remove(shawarma);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addShawarma(Shawarma shawarma) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(shawarma);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Shawarma getShawarmaById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Shawarma.class, id);
    }

    @Override
    public void update(Shawarma shawarma) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(shawarma);
        session.getTransaction().commit();
        session.close();
    }
}
