package by.pvt.shaurma.core.repository.impl;


import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.repository.AdminDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AdminDaoRepository implements AdminDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public AdminDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }

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
         return session.createQuery("select a from Admin a", Admin.class).getResultList();
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
        session.merge(admin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Admin getAdminById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Admin.class, id);
    }
}
