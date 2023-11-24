package com.work.varotra.Repository;


import com.work.varotra.Entity.Demandesociete;
import  com.work.varotra.Entity.Detailledemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailledemandeRepository extends  JpaRepository<Detailledemande,Long> {
    
    @Query(value = "select * from detailledemande where iddemandesociete=:iddemandesociete ", nativeQuery = true)
    public java.util.List<Detailledemande> listedemande(@Param("iddemandesociete") Long iddemandesociete);
    
}
