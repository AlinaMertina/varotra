package com.work.varotra.Work;

import com.work.varotra.Entity.Stock;
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
public class OverViewFacture {
    
    Long idproduit;
    String nomproduit;
    String nomuniter;
    Double quantiter;
    Double prixht;
    Double tva;
    Double prixttc;
    Double prixtotal;

    public java.util.List<OverViewFacture> detailleFacture(Long idfourniseur,Long iddemande,StockRepository stockRepository,TvaetatRepository tvaetatRepository){
        java.util.List<OverViewFacture> resulta = new java.util.ArrayList<OverViewFacture>();
        java.util.List<Stock> listStock= stockRepository.detaillefacture(idfourniseur, iddemande);
        OverViewFacture total = new OverViewFacture();
        Double prixunitaire = 0.0;
        Double prixtvaT=0.0;
        Double prixttcT=0.0;
        Double prixtotalT =0.0;
        for(int i=0;i< listStock.size();i++){
            Double tva1 = (listStock.get(i).getPrixunitairevente()-listStock.get(i).getPrixunitaireachat())*tvaetatRepository.getTva();
            Double prixttc1= listStock.get(i).getPrixunitairevente()+tva1;
            Double prixtotal = prixttc1*listStock.get(i).getQuantiter();

            OverViewFacture overViewFacture = new OverViewFacture(listStock.get(i).getIdproduit(),stockRepository.nomproduit(listStock.get(i).getIdproduit()),stockRepository.nomuniter(listStock.get(i).getIdproduit()),listStock.get(i).getQuantiter(),listStock.get(i).getPrixunitairevente(),tva1,prixttc1,prixtotal);
            resulta.add(overViewFacture);

            prixunitaire=prixunitaire+listStock.get(i).getPrixunitairevente();
            prixtvaT=prixtvaT+tva1;
            prixttcT=prixttcT+prixttc1;
            prixtotalT=prixtotalT+prixtotal;
        }

        total.setPrixht(prixunitaire);
        total.setTva(prixtvaT);
        total.setPrixttc(prixttcT);
        total.setPrixtotal(prixtotalT);
        resulta.add(total);
        return resulta;
    }
}
