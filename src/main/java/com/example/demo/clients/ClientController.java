package com.example.demo.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clients")
public class ClientController {
    final private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getProducts() {
        return clientService.getClients();
    }

    @PostMapping
    public Client addClient(@Valid @RequestBody Client c) {
        Client client = clientService.addClient(c);
        return client;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }
}
