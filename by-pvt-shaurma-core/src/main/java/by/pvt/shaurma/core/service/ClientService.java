package by.pvt.shaurma.core.service;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.entity.Order;
import by.pvt.shaurma.core.mapper.ClientMapper;
import by.pvt.shaurma.core.repository.ClientDao;
import by.pvt.shaurma.core.repository.OrderDao;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements ClientApi {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    public ClientService(ClientDao clientDao, OrderDao orderDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponse register(ClientRequest clientRequest) {
        Client client = clientMapper.mapToClientEntity(clientRequest);
        clientDao.addClient(client);
        return clientMapper.mapToClientDto(client);
    }

    @Override
    public ClientResponse authorise(String login, String password) {
        List<ClientResponse> clients = getAllClients();
        ClientResponse clientResponse = clients.stream().filter(client -> client.getLogin().equals(login)).findFirst().orElseThrow(() -> new RuntimeException("Клиент с логином" + login + " не найден"));
        if (!clientResponse.getPassword().equals(password)) {
            throw new RuntimeException("Не верно введён пароль!");
        }
        return clientResponse;
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
