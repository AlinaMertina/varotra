package com.work.varotra.Repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.varotra.Entity.Connexion;
import com.work.varotra.Entity.Stock;

public interface StockRepository  extends  JpaRepository<Stock,Long>{
    
    @Query(value = "select * from stock where idproduit=:idproduit and idaction=1 or idaction=2", nativeQuery = true)
    java.util.List<Stock> listetsockproduit(@Param("idproduit") Long idproduit);

    @Query(value = "select * from stock where idaction=4 and idfourniseur=:idfourniseur", nativeQuery = true)
    java.util.List<Stock> demandesociete(@Param("idfourniseur") Long idfourniseur);

    @Query(value = "select sum(quantiter) from stock where idfourniseur=:idfourniseur and idproduit=:idproduit and idaction=3", nativeQuery = true)
    public Double resteproduit(@Param("idfourniseur") Long idfourniseur,@Param("idproduit") Long idproduit);

    @Query(value = "select AVG(prixunitairevente) from stock where idfourniseur=:idfourniseur and idproduit=:idproduit", nativeQuery = true)
    public Double prixmoyenne(@Param("idfourniseur") Long idfourniseur,@Param("idproduit") Long idproduit);

    @Query(value = "select (AVG(prixunitaireachat)-AVG(prixunitairevente)) * (select valeur from TVAetat limit 1) as tvam from stock where idfourniseur=:idfourniseur and idproduit=:idproduit", nativeQuery = true)
    public Double tvamoyenne(@Param("idfourniseur") Long idfourniseur,@Param("idproduit") Long idproduit);

    @Query(value = "select distinct(nomproduit) from stock join produit on stock.idproduit=produit.idproduit where produit.idproduit=:idproduit", nativeQuery = true)
    public String nomproduit(@Param("idproduit") Long idproduit);

    @Query(value = "select distinct(nomuniter) from stock join uniter on stock.iduniter=uniter.iduniter where idproduit=:idproduit", nativeQuery = true)
    public String nomuniter(@Param("idproduit") Long idproduit);

    @Query(value = "select distinct(idproduit)  from stock where idfourniseur=:idfourniseur", nativeQuery = true)
    public java.util.List<Long> ensembleproduit(@Param("idfourniseur") Long idfourniseur);

     @Query(value = "select distinct(idfourniseur)  from stock", nativeQuery = true)
    public java.util.List<Long> ensemblefourniseur();

    @Query(value = "select * from stock where idfourniseur=:idfourniseur and idproduit=:idproduit and idaction=3 and quantiter!=0 order by idfstock", nativeQuery = true)
    public java.util.List<Stock> inventaireProduitFourniseur(@Param("idfourniseur") Long idfourniseur,@Param("idproduit") Long idproduit);

    @Query(value = "select * from stock where idfstock=:idfstock order by idaction", nativeQuery = true)
    public java.util.List<Stock> inventaireDemande(@Param("idfstock") Long idfstock);


    @Query(value = "select * from stock where idaction=2 and idfourniseur=:idfourniseur and iddemandesociete=:iddemandesociete", nativeQuery = true)
    java.util.List<Stock> detaillefacture(@Param("idfourniseur") Long idfourniseur,@Param("iddemandesociete") Long iddemandesociete);

    @Query(value = "select distinct(idfourniseur) from stock where idaction=2  and iddemandesociete=:iddemandesociete", nativeQuery = true)
    java.util.List<Long> fourniseurdemande(@Param("iddemandesociete") Long iddemandesociete);



}
