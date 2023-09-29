package com.clients.cip.repository;

import com.clients.cip.dao.ClientDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDAO, Long> {

}
