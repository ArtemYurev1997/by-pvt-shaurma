package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.repository.ClientDao;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClientDaoRepository implements ClientDao {
    @Autowired
    private SessionFactory sessionFactory;

//    public ClientDaoRepository() {
//        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
//    }

    @Override
    public void addClient(Client client) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Client> getAllClients() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select c from Client c", Client.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.getTransaction().begin();
        session.remove(client);
        session.getTransaction().commit();
    }

    @Override
    public void update(Client client) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Client getClientById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Client.class, id);
    }
}
