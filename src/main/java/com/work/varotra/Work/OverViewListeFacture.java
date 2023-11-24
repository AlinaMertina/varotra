package com.work.varotra.Work;

import com.work.varotra.Entity.Demandesociete;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.FourniseurRepository;
import com.work.varotra.Repository.StockRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewListeFacture {
    Long idfourniseur;
    Long iddemandesociete;
    String nomfourniseur;
    java.util.Date date;

    public java.util.List<OverViewListeFacture> getlistefacture(Long idsup,StockRepository stockRepository,DemandesocieteRepository demandesocieteRepository,FourniseurRepository fourniseurRepository){
            java.util.List<OverViewListeFacture> resulta = new java.util.ArrayList<OverViewListeFacture>();
            java.util.List<Demandesociete> listefacturedemande = demandesocieteRepository.listefacturedemande(idsup);
            for(int i=0;i<listefacturedemande.size();i++){
                java.util.List<Long> liststock = stockRepository.fourniseurdemande(listefacturedemande.get(i).getIddemandesociete());
                for(int a=0 ;a<liststock.size();a++){
                    OverViewListeFacture overViewListeFacture = new OverViewListeFacture(liststock.get(a),listefacturedemande.get(i).getIddemandesociete(),fourniseurRepository.findById(liststock.get(a)).get().getNomfourniseur(),listefacturedemande.get(i).getDatedemande());
                    resulta.add(overViewListeFacture);
                }
            }
            return resulta;
    }

}
