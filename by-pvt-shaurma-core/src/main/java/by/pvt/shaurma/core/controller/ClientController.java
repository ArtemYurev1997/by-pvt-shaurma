package by.pvt.shaurma.core.controller;

import by.pvt.shaurma.api.contract.ClientApi;
import by.pvt.shaurma.api.dto.ClientRequest;
import by.pvt.shaurma.api.dto.ClientResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientApi clientApi;

    public ClientController(@Qualifier("clientServiceApi") ClientApi clientApi) {
        this.clientApi = clientApi;
    }


    @GetMapping("/getAll")
    public List<ClientResponse> findAll() {
        return clientApi.getAllClients();
        }

    @PostMapping("/add")
    public ClientResponse add(@RequestBody ClientRequest clientRequest) {
        return clientApi.register(clientRequest);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        clientApi.delete(id);
    }


    @PostMapping("/update")
    public List<ClientResponse> update(ClientRequest clientRequest) {
        return clientApi.update(clientRequest);
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable("id") Long id) {
        return clientApi.findClientById(id);
    }

    @PostMapping("/authorise")
    public ClientResponse authorise( @RequestBody ClientRequest clientRequest) {
        String login = clientRequest.getLogin();
        String password = clientRequest.getPassword();
        return clientApi.authorise(login, password);
    }
}
