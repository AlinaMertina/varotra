package com.work.varotra.Work;

import org.springframework.data.repository.query.Param;

import com.work.varotra.Entity.Demandesociete;
import com.work.varotra.Entity.Stock;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Repository.TvaetatRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewDemandeFourniseur {
    Long idstock;
    Long iddemandesocieter;
    String nomclient;
    String nomproduit;
    String nomtypepayement;
    Double quantiter;
    Double prixvente;
    Double tva ;
    Double prixttc;
    Double prixtotal;
    java.util.Date datecommande;
    java.util.Date datedelait;
    String nomuniter;

    public java.util.List<OverViewDemandeFourniseur> getDemandeClient(Long idfourniseur,StockRepository stockRepository,DemandesocieteRepository demandesocieteRepository,TvaetatRepository tvaetatRepository){
        java.util.List<Stock> listedemande = stockRepository.demandesociete(idfourniseur);
        java.util.List<OverViewDemandeFourniseur> resulta = new java.util.ArrayList<OverViewDemandeFourniseur>();
        for(int i=0;i< listedemande.size();i++){
            Demandesociete demandesociete = demandesocieteRepository.findById(listedemande.get(i).getIddemandesociete()).get();
            Long iddemandesocieter1=listedemande.get(i).getIddemandesociete();
            String nomclient1=demandesocieteRepository.nomclient(iddemandesocieter1);
            String nomproduit1=stockRepository.nomproduit(listedemande.get(i).getIdproduit());
            String nomtypepayement1=demandesocieteRepository.nomtypepayement(iddemandesocieter1);
            Double quantiter1=listedemande.get(i).getQuantiter();
            Double prixvente1=listedemande.get(i).getPrixunitairevente();
            Double tva1= (listedemande.get(i).getPrixunitairevente()-listedemande.get(i).getPrixunitaireachat())*tvaetatRepository.getTva();
            Double prixttc1=prixvente1+tva1;
            Double prixtotal1=prixttc1*quantiter1;
            java.util.Date datecommande1=demandesociete.getDatedemande();
            java.util.Date datedelait1=demandesociete.getDelait();
            OverViewDemandeFourniseur overViewDemandeFourniseur = new OverViewDemandeFourniseur(listedemande.get(i).getIdstock(),iddemandesocieter1,nomclient1,nomproduit1,nomtypepayement1,quantiter1,prixvente1,tva1,prixttc1,prixtotal1,datecommande1,datedelait1,stockRepository.nomuniter(listedemande.get(i).getIdproduit()));
            resulta.add(overViewDemandeFourniseur);
        }
        return resulta;
    }

}
