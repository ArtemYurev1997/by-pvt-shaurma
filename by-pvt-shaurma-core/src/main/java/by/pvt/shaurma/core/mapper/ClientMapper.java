package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponse mapToClientDto(Client client) {
        ClientResponse dto = new ClientResponse();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setLogin(client.getLogin());
        dto.setRole(client.getRole());
        dto.setFirstVisit(client.getFirstVisit());
        dto.setLastVisit(client.getLastVisit());
        dto.setTelephone(client.getTelephone());
        dto.setAmountSpent(client.getAmountSpent());
        return dto;
    }

    public Client mapToClientEntity(ClientRequest clientRequest) {
        Client dto = new Client();
        dto.setFirstVisit(clientRequest.getFirstVisit());
        dto.setLastVisit(clientRequest.getLastVisit());
        dto.setTelephone(clientRequest.getTelephone());
        dto.setAmountSpent(clientRequest.getAmountSpent());
        return dto;
    }
}
