package com.clients.cip.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ClientsWithLists {
    private Long clientID;
    private List<ListsInfo> lists;
}
