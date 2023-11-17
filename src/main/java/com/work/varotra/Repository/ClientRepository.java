package com.work.varotra.Repository;

import com.work.varotra.Entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends  JpaRepository<Client,Long>  {
    
}
