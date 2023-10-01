package com.clients.cip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "id")
    private Long id;

    @Column(name = "list_id")
    private Long listID;

    @Column(name = "listed_dttm")
    private LocalDateTime listedDTTM;

    @ManyToMany
    @JoinTable(
            name = "clients_with_lists",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "listing_id")
    )
    private Set<Listing> lists = new HashSet<>();

    public Client(Long clientID, Long lid, LocalDateTime now) {
        this.id =clientID;
        this.listID = lid;
        this.listedDTTM = now;
    }
}
