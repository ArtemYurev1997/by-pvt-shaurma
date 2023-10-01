package by.pvt.shaurma.core.repository.impl;

import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.repository.ClientDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    public ClientDaoRepository() {
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

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
        List<Client> clients = session.createQuery("select c from Client c").getResultList();
        session.close();
        return clients;
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
        session.update(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Client getClientById(Long id) {
        Session session = sessionFactory.openSession();
        return session.get(Client.class, id);
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
