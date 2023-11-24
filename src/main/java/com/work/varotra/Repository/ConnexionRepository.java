package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.varotra.Entity.Connexion;


public interface ConnexionRepository extends  JpaRepository<Connexion,Long>{

    @Query(value = "select * from connexion where idemploye=:idemploye and password=:password", nativeQuery = true)
    public java.util.Optional<Connexion> findByLogin(@Param("idemploye") Long idemploye,@Param("password") String password);
}
