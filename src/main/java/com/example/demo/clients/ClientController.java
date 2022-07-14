package com.example.demo.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{pageNo}/{pageSize}")
    public List<Client> getClients(@PathVariable int pageNo,
                                   @PathVariable int pageSize) {
        return clientService.getClients(pageNo, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Client addClient(@Valid @RequestBody Client c) {
        c.setId(0L);
        Client client = clientService.addClient(c);
        return client;
    }

    @PutMapping("/{id}")
    public Client editClient(@Valid @RequestBody Client c, @PathVariable("id") Long id){
        if(!clientService.checkIfExists(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Client client = clientService.updateClient(c, id);
        return client;
    }
}
