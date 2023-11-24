package com.work.varotra.Work;

import com.work.varotra.Repository.StockRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewProduit {
    Long idproduit;
    String nomproduit;
    String nomuniter;
    Double quantiterestant;
    Double prixmoyenne;
    Double tvamoyenne;


    public java.util.List<OverViewProduit> getlisteOverViewProduit(Long idfourniseur,StockRepository stockRepository){
        java.util.List<OverViewProduit> resulta = new java.util.ArrayList<OverViewProduit>();
        java.util.List<Long> idproduit = stockRepository.ensembleproduit(idfourniseur);
        for(int i=0;i<idproduit.size();i++){
            Long id=idproduit.get(i);
            OverViewProduit overViewProduit = new OverViewProduit(id, stockRepository.nomproduit(id),stockRepository.nomuniter(id),stockRepository.resteproduit(idfourniseur, id),stockRepository.prixmoyenne(idfourniseur, id), stockRepository.tvamoyenne(idfourniseur, id));
            resulta.add(overViewProduit);
        }
        return resulta;
    }
}
