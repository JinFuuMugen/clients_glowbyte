package com.clients.cip.service;

import com.clients.cip.model.Client;
import com.clients.cip.model.ClientsWithLists;
import com.clients.cip.model.Listing;
import com.clients.cip.model.ListsInfo;
import com.clients.cip.repository.ClientRepository;
import com.clients.cip.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ListingRepository listingRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ListingRepository listingRepository) {
        this.clientRepository = clientRepository;
        this.listingRepository = listingRepository;
    }

    public List<ClientsWithLists> getAllClientsWithLists() {
        List<Long> clientsIDs = clientRepository.findAllClientsID();

        List<ClientsWithLists> clientsWithLists = new ArrayList<>();

        for (Long id : clientsIDs){
            List<ListsInfo> listsInfo = clientRepository.findAllListsByClientID(id);
            clientsWithLists.add(new ClientsWithLists(id, listsInfo));
        }
        return clientsWithLists;
    }

    public List<Client> getClientByIDAndListname(Long clientID, String listName){
        return clientRepository.findClientByIdAndListname(clientID, listName);
    }

    public List<Client> getClientByID(Long clientID){
        return clientRepository.findClientById(clientID);
    }

    public ClientsWithLists getOneClientWithLists(Long clientId){
        List<ListsInfo> listsInfo = clientRepository.findAllListsByClientID(clientId);
        if (listsInfo.isEmpty()){
            return new ClientsWithLists(clientId, new ArrayList<>());
        } else {
            return new ClientsWithLists(clientId, listsInfo);
        }
    }

    public void deleteClientByIDAndListName(Long clientId, String listname){
        clientRepository.deleteByIdAndListName(clientId, listname);
    }

    public void createClient(Long clientID, String listName){
        List<Long> listsIDS = clientRepository.getListIDByName(listName);
        if (listsIDS.isEmpty()){
            listingRepository.save(new Listing(listName));
        }
        listsIDS = clientRepository.getListIDByName(listName);
        for (Long lid : listsIDS){
            clientRepository.save(new Client(clientID, lid, LocalDateTime.now()));
        }
    }
}