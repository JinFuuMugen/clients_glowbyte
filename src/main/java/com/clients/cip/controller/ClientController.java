package com.clients.cip.controller;

import com.clients.cip.model.ClientsWithLists;
import com.clients.cip.requestbody.ListRequest;
import com.clients.cip.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/clients")
    public ResponseEntity<List<ClientsWithLists>> getAllClientsWithLists() {
        List<ClientsWithLists> clientInfoList = clientService.getAllClientsWithLists();
        if (clientInfoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientInfoList, HttpStatus.OK);
    }

    @GetMapping(path = "/client/{clientId}")
    public ResponseEntity<ClientsWithLists> getOneClientWithLists(@PathVariable Long clientId){
        if (clientService.getClientByID(clientId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientsWithLists clientWithLists = clientService.getOneClientWithLists(clientId);
        return new ResponseEntity<>(clientWithLists, HttpStatus.OK);
    }
    @DeleteMapping(path = "/client/{clientId}")
    public ResponseEntity<Void> deleteClientByIDAndListName(@PathVariable Long clientId, @RequestBody  ListRequest listName){
        if (clientService.getClientByIDAndListname(clientId, listName.getListName()).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clientService.deleteClientByIDAndListName(clientId, listName.getListName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/client/{clientId}")
    public ResponseEntity<Void> addClientToListing(@PathVariable Long clientId, @RequestBody ListRequest listName){
        clientService.createClient(clientId, listName.getListName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}