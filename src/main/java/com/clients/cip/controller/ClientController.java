package com.clients.cip.controller;

import com.clients.cip.model.Client;
import com.clients.cip.model.ClientsWithLists;
import com.clients.cip.repository.ClientRepository;
import com.clients.cip.repository.ListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    private final ClientRepository clientRepository;
    private final ListRepository listRepository;

    public ClientController(ClientRepository clientRepository, ListRepository listRepository){
        this.clientRepository = clientRepository;
        this.listRepository = listRepository;
    }

    @GetMapping(path = "/clients")
    ResponseEntity<List<ClientsWithLists>> getAllClients(){
        List<Client> clientsList = clientRepository.findAll();


        List<ClientsWithLists> clientsWithList = new ArrayList<>();
        for (Client c : clientsList){
            clientsWithList.add(new ClientsWithLists(c.getId(), null));
        }

        return new ResponseEntity<>(clientsWithList, HttpStatus.OK);
    }

    //TODO: add other endpoints
}