package com.cyberaakash.multidatasource.server.repository;

import com.cyberaakash.multidatasource.server.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server,Long> {
}
