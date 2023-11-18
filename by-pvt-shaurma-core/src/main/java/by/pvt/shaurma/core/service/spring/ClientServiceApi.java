package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import by.pvt.shaurma.core.entity.Admin;
import by.pvt.shaurma.core.entity.Client;
import by.pvt.shaurma.core.exception.AccountException;
import by.pvt.shaurma.core.mapper.spring.ClientMappers;
import by.pvt.shaurma.core.repository.spring.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
//@Primary
public class ClientServiceApi implements ClientApi, UserDetailsService {
    private final ClientRepository clientRepository;
    private final ClientMappers clientMappers;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ClientResponse register(ClientRequest clientRequest) {
        if (clientRequest.getName().equals("") | clientRequest.getSurname().equals("") | clientRequest.getLogin().equals("") | clientRequest.getPassword().equals("")) {
            throw new AccountException("Введите обязательные поля!");
        }
        if (clientRequest.getLogin() != null) {
            throw new AccountException("Логин занят!");
        }
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.loadUserByUserName(login);
        UserDetails user = User.builder()
                .username(client.getLogin())
                .password(client.getPassword())
                .roles(client.getRole()).authorities(client.getAuthorities())
                .build();
        return user;
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
    @Secured({"ADMIN"})
    public List<ClientResponse> update(ClientRequest clientRequest) {
        Optional<Client> client = clientRepository.findById(clientRequest.getId());
        String password = passwordEncoder.encode(clientRequest.getPassword());
        if (client.isPresent()) {
            client.get().setPassword(password);
            clientRepository.save(client.get());
        }
        return getAllClients();
    }
}
