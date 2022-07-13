package com.example.demo.clients;

import com.example.demo.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ClientService {

    final private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client c) {
        Client client = clientRepository.save(c);
        return client;
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).get();
    }
}
