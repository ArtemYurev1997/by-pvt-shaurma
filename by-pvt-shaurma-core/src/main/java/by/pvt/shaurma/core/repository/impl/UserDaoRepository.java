package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.User;
import by.pvt.shaurma.core.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoRepository implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> clients = session.createQuery("select u from User u").getResultList();
        session.close();
        return clients;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.getTransaction().begin();
        session.remove(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getClientById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        return user;
    }

}
