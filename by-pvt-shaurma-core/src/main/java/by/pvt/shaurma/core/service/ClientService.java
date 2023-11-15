package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.exception.TransactionException;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import org.hibernate.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements ClientApi, UserDetailsService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;
    private final SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public ClientService(ClientDao clientDao, ClientMapper clientMapper, SessionFactory sessionFactory) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ClientResponse register(ClientRequest clientRequest) {
        if(clientRequest.getName().equals("") | clientRequest.getSurname().equals("") | clientRequest.getLogin().equals("") | clientRequest.getPassword().equals("")) {
            throw new AccountException("Введите обязательные поля!");
        }
        else if(clientRequest.getLogin() != null) {
            throw new AccountException("Логин занят!");
        }
        Client client = clientMapper.mapToClientEntity(clientRequest);
        clientDao.addClient(client);
        return clientMapper.mapToClientDto(client);
    }

    @Override
    public ClientResponse authorise(String login, String password) {
            try{
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                Client client = session.createQuery("From Client where login = :login and password = :password", Client.class).setParameter("login", login).setParameter("password", password).uniqueResult();
                if(client != null){
                    return clientMapper.mapToClientDto(client);
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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Client client = session.createQuery("From Client where login = :login", Client.class).setParameter("login", login).getSingleResult();
            if(client != null){
                return client;
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
