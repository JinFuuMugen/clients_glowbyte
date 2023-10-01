package com.clients.cip.repository;

import com.clients.cip.model.Client;
import com.clients.cip.model.ListsInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT DISTINCT(c.id) FROM Client c")
    List<Long> findAllClientsID();

    @Query("SELECT NEW com.clients.cip.model.ListsInfo(l.name, c.listedDTTM) FROM Client c LEFT JOIN Listing l ON c.listID = l.id WHERE c.id = :cid")
    List<ListsInfo> findAllListsByClientID(@Param("cid") Long cid);

    @Query("SELECT c FROM Client c LEFT JOIN Listing l ON c.listID = l.id WHERE c.id = :cid ")
    List<Client> findClientById(@Param("cid") Long cid);

    @Query("SELECT c FROM Client c LEFT JOIN Listing l ON c.listID = l.id WHERE c.id = :cid AND l.name = :name")
    List<Client> findClientByIdAndListname(@Param("cid") Long cid, @Param("name") String listName);


    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.id = :cid AND c.listID IN (SELECT DISTINCT(l.id) FROM Listing l WHERE l.name = :name)")
    void deleteByIdAndListName(@Param("cid") Long cid, @Param("name") String name);


    @Query("SELECT DISTINCT(l.id) FROM Listing l WHERE l.name = :name")
    List<Long> getListIDByName(@Param("name") String name);
}
