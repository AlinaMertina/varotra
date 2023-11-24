package com.work.varotra.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.varotra.Entity.Connexion;
import com.work.varotra.Entity.Demandesociete;

public interface DemandesocieteRepository  extends  JpaRepository<Demandesociete,Long>{
    
    @Query(value = "select * from demandesociete where validationsup in ( select idemploye  from employe where idsupe=:idsupe )  and delait >= now() ", nativeQuery = true)
    public java.util.List<Demandesociete> listedemande(@Param("idsupe") Long idsupe);

    @Query(value = "select nomclient from demandesociete join employe on employe.idemploye=demandesociete.validationsup join client on employe.idclient=client.idclient where iddemandesociete=:iddemandesociete", nativeQuery = true)
    public String nomclient(@Param("iddemandesociete") Long iddemandesociete);

    @Query(value = "select distinct(nompayement) from demandesociete join typedepayement on demandesociete.idtypedepayement=typedepayement.idtypedepayement where iddemandesociete=:iddemandesociete", nativeQuery = true)
    public String nomtypepayement(@Param("iddemandesociete") Long iddemandesociete);

     @Query(value = "select * from demandesociete where validationsup=:validationsup", nativeQuery = true)
    public java.util.List<Demandesociete> listefacturedemande(@Param("validationsup") Long validationsup);
}
