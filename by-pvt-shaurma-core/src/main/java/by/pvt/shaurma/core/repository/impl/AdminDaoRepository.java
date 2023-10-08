package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.repository.AdminDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDaoRepository implements AdminDao {
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public AdminDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    public Admin authorise(String login, String password) {
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Admin admin = (Admin) session.createQuery("From Client where login = :login and password = :password").setParameter("login", login).setParameter("password", password).uniqueResult();
            if(admin != null){
                return admin;
            }else{
                return null;
            }
        }catch(HibernateException e){
            transaction.rollback();
            System.out.println("Transaction is rolled back.");
            return null;
        }
        finally{
            session.close();
        }
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
        session.close();
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
        return session.get(Admin.class, id);
    }
}
