package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;

public class ClientMapper {
    public ClientResponse mapToClientDto(Client client) {
        ClientResponse dto = new ClientResponse();
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
