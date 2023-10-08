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

    Client authorise(String login, String password);

    List<Client> findAllClientsWhereAmountSpentGreaterThan(BigDecimal amountSpent);

    List<Client> findAllClientsByNameAndAmountSpent(String name, BigDecimal amountSpent);

    List<Client> findAllClientsByNameForDetach(String name);
}
