package com.cyberaakash.multidatasource.client.repository;

import com.cyberaakash.multidatasource.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
