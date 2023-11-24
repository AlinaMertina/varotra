package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.varotra.Entity.Employe;

public interface EmployeRepositor extends  JpaRepository<Employe,Long>{
    @Query(value = "select nomdepartment from employe join departement on employe.iddepartement=departement.iddepartement where idemploye=:idemploye", nativeQuery = true)
    String nomDepartement(@Param("idemploye") Long idemploye);

}
