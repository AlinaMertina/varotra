package com.work.varotra.Work;

import com.work.varotra.Entity.Stock;
import com.work.varotra.Entity.Detailledemande;
import com.work.varotra.Repository.DetailledemandeRepository;
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
public class OverViewProformat {
    String nomproduit;
    String uniter;
    Double quantiter;
    Double prixunitaire;
    Double tva;
    Double prixttc;
    public java.util.List<OverViewProformat> getProformatsFourniseur(Long iddemandesociete,Long idfourniseur ,StockRepository stockRepository,DetailledemandeRepository detailledemandeRepository,TvaetatRepository tvaetatRepository){
        java.util.List<OverViewProformat> resulta = new java.util.ArrayList<OverViewProformat>();
        java.util.List<Detailledemande> detailledemande=  detailledemandeRepository.listedemande(iddemandesociete);
        for(int i=0;i<detailledemande.size();i++){
            java.util.List<Stock> produitrestant = stockRepository.inventaireProduitFourniseur(idfourniseur, detailledemande.get(i).getIdproduit());
            for(int a=0;a<produitrestant.size();a++){
                Double tva1= (produitrestant.get(a).getPrixunitairevente()-produitrestant.get(a).getPrixunitaireachat())*tvaetatRepository.getTva();
                Double ttc1 = produitrestant.get(a).getPrixunitairevente()+tva1;
                    OverViewProformat overViewProformat = new OverViewProformat(stockRepository.nomproduit(detailledemande.get(i).getIdproduit()),stockRepository.nomuniter(detailledemande.get(i).getIdproduit()),produitrestant.get(a).getQuantiter(),produitrestant.get(a).getPrixunitairevente(),tva1,ttc1);
                    resulta.add(overViewProformat);
            }
        }
        return resulta;
    }
    
}
