package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.repository.ClientDao;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class ClientDaoRepository implements ClientDao {
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;


    public ClientDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public void addClient(Client client) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Client> getAllClients() {
        session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("select c from Client c").getResultList();
        session.close();
        return clients;
    }

    @Override
    public void delete(Long id) {
        session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.getTransaction().begin();
        session.remove(client);
        session.getTransaction().commit();
    }

    @Override
    public void update(Client client) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Client getClientById(Long id) {
        session = sessionFactory.openSession();
        return session.get(Client.class, id);
    }

    public Client authorise(String login, String password) {
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Client client = (Client) session.createQuery("From Client where login = :login and password = :password").setParameter("login", login).setParameter("password", password).uniqueResult();
            if(client != null){
                return client;
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

    public List<Client> findAllClientsWhereAmountSpentGreaterThan(BigDecimal amountSpent) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        criteriaQuery.select(client).where(criteriaBuilder.gt(client.get("amountSpent"), amountSpent)).orderBy(criteriaBuilder.asc(client.get("amountSpent")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Client> findAllClientsByNameAndAmountSpent(String name, BigDecimal amountSpent) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        criteriaQuery.select(client).where(criteriaBuilder.and(criteriaBuilder.equal(client.get("amountSpent"), amountSpent), criteriaBuilder.equal(client.get("name"), name)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Client> findAllClientsByNameForDetach(String name) {
        DetachedCriteria clients = DetachedCriteria.forClass(Client.class);
        clients.add(Restrictions.eq("name", name));
        EntityManager entityManager = sessionFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = clients.getExecutableCriteria(session);
        return (List<Client>) criteria.list();
    }
}
