package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.AdminApi;
import by.pvt.shaurma.api.dto.AdminRequest;
import by.pvt.shaurma.api.dto.AdminResponse;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.exception.TransactionException;
import by.pvt.shaurma.core.mapper.AdminMapper;
import by.pvt.shaurma.core.repository.AdminDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService implements AdminApi, UserDetailsService {
    private final AdminDao adminDao;
    private final AdminMapper adminMapper;
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public AdminService(AdminDao adminDao, AdminMapper adminMapper, SessionFactory sessionFactory) {
        this.adminDao = adminDao;
        this.adminMapper = adminMapper;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public AdminResponse register(AdminRequest adminRequest) {
        if(adminRequest.getName().equals("") | adminRequest.getSurname().equals("") | adminRequest.getLogin().equals("") | adminRequest.getPassword().equals("")) {
            throw new AccountException("Введите обязательные поля!");
        }
        else if(adminRequest.getLogin() != null) {
            throw new AccountException("Логин занят!");
        }
        Admin admin = adminMapper.mapToAdminEntity(adminRequest);
        adminDao.addUser(admin);
        return adminMapper.mapToAdminDto(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Admin admin = session.createQuery("From Admin where login = :login", Admin.class).setParameter("login", login).getSingleResult();
            if(admin != null){
                return admin;
            }else{
                throw new AccountException("Пользователь не найден!");
            }
        }catch(HibernateException e){
            transaction.rollback();
            throw new TransactionException("Транзакция отклонена!");
        }
        finally{
            session.close();
        }
    }

//    @Override
    public AdminResponse authorise(AdminRequest adminRequest) {
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Admin admin = session.createQuery("From Admin where login = :login and password = :password", Admin.class).setParameter("login", adminRequest.getLogin()).setParameter("password", adminRequest.getPassword()).uniqueResult();
            if(admin != null){
                return adminMapper.mapToAdminDto(admin);
            }else{
                throw new AccountException("Пользователь не найден!");
            }
        }catch(HibernateException e){
            transaction.rollback();
            throw new TransactionException("Транзакция отклонена!");
        }
        finally{
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        adminDao.delete(id);
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminDao.getAllAdmins().stream().map(adminMapper::mapToAdminDto).collect(Collectors.toList());
    }

    @Override
    public AdminResponse findAdminById(Long id) {
        return adminMapper.mapToAdminDto(adminDao.getAdminById(id));
    }



    @Override
    public List<AdminResponse> update(AdminRequest adminRequest) {
        adminDao.update(adminMapper.mapToAdminEntity(adminRequest));
        return getAllAdmins();
    }

    public List<Client> findAllClientsWhereAmountSpentGreaterThan(BigDecimal amountSpent) {
        EntityManager entityManager = (EntityManager) sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        criteriaQuery.select(client).where(criteriaBuilder.gt(client.get("amountSpent"), amountSpent)).orderBy(criteriaBuilder.asc(client.get("amountSpent")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Client> findAllClientsByNameAndAmountSpent(String name, BigDecimal amountSpent) {
        EntityManager entityManager = (EntityManager) sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> client = criteriaQuery.from(Client.class);
        criteriaQuery.select(client).where(criteriaBuilder.and(criteriaBuilder.equal(client.get("amountSpent"), amountSpent), criteriaBuilder.equal(client.get("name"), name)));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

//    public List<Client> findAllClientsByNameForDetach(String name) {
//        DetachedCriteria clients = DetachedCriteria.forClass(Client.class);
//        clients.add(Restrictions.eq("name", name));
//        EntityManager entityManager = (EntityManager) sessionFactory.createEntityManager();
//        Session session = entityManager.unwrap(Session.class);
//        return clients.getExecutableCriteria(session).list();
//    }
}
