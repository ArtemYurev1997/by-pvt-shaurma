package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.config.HibernateJavaConfiguration;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements ClientApi {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public ClientService(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.sessionFactory = HibernateJavaConfiguration.getSessionFactory();
    }

    @Override
    public ClientResponse register(ClientRequest clientRequest) {
        Client client = clientMapper.mapToClientEntity(clientRequest);
        clientDao.addClient(client);
        return clientMapper.mapToClientDto(client);
    }

    @Override
    public ClientResponse authorise(String login, String password) {
            try{
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                Client client = (Client) session.createQuery("From Client where login = :login and password = :password").setParameter("login", login).setParameter("password", password).uniqueResult();
                if(client != null){
                    return clientMapper.mapToClientDto(client);
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
    public void delete(Long id) {
        clientDao.delete(id);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientDao.getAllClients().stream().map(clientMapper::mapToClientDto).collect(Collectors.toList());
    }

    @Override
    public ClientResponse findClientById(Long id) {
        return clientMapper.mapToClientDto(clientDao.getClientById(id));
    }

    @Override
    public List<ClientResponse> update(ClientRequest clientRequest) {
        clientDao.update(clientMapper.mapToClientEntity(clientRequest));
        return getAllClients();
    }

}
