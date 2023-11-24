package com.work.varotra.Work;

import com.work.varotra.Entity.Detailledemande;
import com.work.varotra.Repository.DetailledemandeRepository;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.UniterRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewDemandeDetaille {
    Long iddemandesociete;
    String nomproduit;
    Double quantier;
    String uniter;
    public java.util.List<OverViewDemandeDetaille> listedetailledemande(Long iddemandesociete,DetailledemandeRepository detailledemandeRepository,ProduitRepository produitRepository,UniterRepository uniterRepository){
        java.util.List<Detailledemande> detailledemande = detailledemandeRepository.listedemande(iddemandesociete);
        java.util.List<OverViewDemandeDetaille> resulta = new java.util.ArrayList<OverViewDemandeDetaille>();
        for(int i=0;i<detailledemande.size();i++){
            OverViewDemandeDetaille overViewDemandeDetaille = new OverViewDemandeDetaille(iddemandesociete,produitRepository.findById(detailledemande.get(i).getIdproduit()).get().getNomproduit(),detailledemande.get(i).getQuantiter(),uniterRepository.findById(detailledemande.get(i).getIduniter()).get().getNomuniter());
            resulta.add(overViewDemandeDetaille);
        }
        return resulta;
    }
}
