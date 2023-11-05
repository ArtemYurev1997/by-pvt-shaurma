package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.mapper.spring.ClientMappers;
import by.pvt.shaurma.core.repository.spring.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientServiceApi implements ClientApi {
    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;

    @Override
    @Transactional
    public ClientResponse register(ClientRequest clientRequest) {
        if(clientRequest.getName().equals("") | clientRequest.getSurname().equals("") | clientRequest.getLogin().equals("") | clientRequest.getPassword().equals("")) {
            throw new AccountException("Введите обязательные поля!");
        }
//        else if(clientRequest.getLogin() != null) {
//            throw new AccountException("Логин занят!");
//        }
        Client client = clientMappers.toEntity(clientRequest);
        clientRepository.save(client);
        return clientMappers.toResponse(client);
    }

    @Transactional
    @Override
    public ClientResponse authorise(String login, String password) {
        Client client = clientRepository.authorise(login, password);
        if (client == null) {
            throw new AccountException("Пользователь не найден!");
        }
        return clientMappers.toResponse(client);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream().map(clientMappers::toResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse findClientById(Long id) {
        Optional<ClientResponse> clientResponse = Optional.of(clientMappers.toResponse(clientRepository.findById(id).orElseThrow(() -> new AccountException("404!"))));
        return clientResponse.get();
    }

    @Override
    @Transactional
    public List<ClientResponse> update(ClientRequest clientRequest) {
        clientRepository.save(clientMappers.toEntity(clientRequest));
        return getAllClients();
    }
}
