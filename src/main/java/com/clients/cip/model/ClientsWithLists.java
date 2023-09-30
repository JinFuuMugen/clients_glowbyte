package com.clients.cip.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientsWithLists {
    private Long clientID;
    private List<Listing> listOfClients;
}
