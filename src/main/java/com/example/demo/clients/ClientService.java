package com.example.demo.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    final private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients(int pageNo, int pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return clientRepository.findAll(paging).toList();
    }

    public Client addClient(Client c) {
        Client client = clientRepository.save(c);
        return client;
    }

    public Client updateClient(Client c, Long id){
        Client client = clientRepository.findById(id).get();
        client.setName(c.getName());
        client.setLast_name(c.getLast_name());
        client.setMobile(c.getMobile());
        clientRepository.save(client);
        return client;
    }

    public boolean checkIfExists(Long id){
        return clientRepository.findById(id).isPresent();
    }

}
