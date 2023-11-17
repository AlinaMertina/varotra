package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.varotra.Entity.Employe;

public interface EmployeRepositor extends  JpaRepository<Employe,Long>{
    
}
