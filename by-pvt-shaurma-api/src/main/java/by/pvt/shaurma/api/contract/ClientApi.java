package by.pvt.shaurma.api.contract;


import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;


import java.util.List;

public interface ClientApi {
    ClientResponse register(ClientRequest clientRequest);

    ClientResponse authorise(String login, String password);

    void delete(Long id);

    List<ClientResponse> getAllClients();

    ClientResponse findClientById(Long id);

    List<ClientResponse> update(ClientRequest clientRequest);
}
