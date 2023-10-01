package com.clients.cip.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ListsInfo {
    private String listName;
    private LocalDateTime listedDTTM;


}
