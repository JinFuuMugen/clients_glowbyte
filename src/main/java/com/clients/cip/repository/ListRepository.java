package com.clients.cip.repository;

import com.clients.cip.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<Listing, Long> {
}
