package by.pvt.shaurma.core.repository;

import by.pvt.shaurma.core.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientDao  {
    void addClient(Client client);

    List<Client> getAllClients();

    void delete(Long id);

    void update(Client client);

    Client getClientById(Long id);
}
