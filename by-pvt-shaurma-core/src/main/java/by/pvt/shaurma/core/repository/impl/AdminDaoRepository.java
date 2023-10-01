package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.entity.User;
import by.pvt.shaurma.core.repository.AdminDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AdminDaoRepository implements AdminDao {
    private final SessionFactory sessionFactory;

    public AdminDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public void addUser(Admin admin) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(admin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Admin> getAllAdmins() {
        Session session = sessionFactory.openSession();
        List<Admin> clients = session.createQuery("select a from Admin a").getResultList();
        session.close();
        return clients;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class, id);
        session.getTransaction().begin();
        session.remove(admin);
        session.getTransaction().commit();
    }

    @Override
    public void update(Admin admin) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(admin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Admin getAdminById(Long id) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class, id);
        return admin;
    }
}
