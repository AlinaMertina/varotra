package com.work.varotra.Work;

import com.work.varotra.Entity.Detailledemande;
import com.work.varotra.Entity.Stock;
import com.work.varotra.Repository.DetailledemandeRepository;
import com.work.varotra.Repository.FourniseurRepository;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Repository.TvaetatRepository;
import com.work.varotra.Repository.UniterRepository;
import com.work.varotra.Entity.Fourniseur;
import com.work.varotra.Entity.Produit;
import com.work.varotra.Entity.Uniter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewMeilleurFourniseur {
    Long iddemandesociete;
    String nomfourniseur;
    String nomproduit;
    String uniter;
    Double quantiter;
    Double prix;
    Double tva;
    Double prixttc;
    Double prixtotal;
    Long idfourniseur;


    public java.util.List<OverViewMeilleurFourniseur> listeMeilleurFourniseur(Long iddemandesociete,DetailledemandeRepository detailledemandeRepository,UniterRepository uniterRepository,ProduitRepository produitRepository,FourniseurRepository fourniseurRepository,StockRepository stockRepository,TvaetatRepository tvaetatRepository) throws Exception{
            java.util.List<Detailledemande> detailledemande = detailledemandeRepository.listedemande(iddemandesociete);
            StockWork stockWork = new StockWork();
            java.util.List<OverViewMeilleurFourniseur> resulta = new java.util.ArrayList<OverViewMeilleurFourniseur>();
            OverViewMeilleurFourniseur totalfinale = new OverViewMeilleurFourniseur();
            Double tvasomme=0.0;
            Double prixttcT= 0.0;
            Double totalp =0.0;
           for(int i=0;i<detailledemande.size();i++){
                Stock stock = new Stock(null, null, detailledemande.get(i).getIdproduit(), null,null,detailledemande.get(i).getQuantiter(), null, null, null, null,null);
                java.util.List<Stock> temps = stockWork.detailleMutliFourniseur(stock, stockRepository);
                for(int a=0;a<temps.size();a++){
                    Stock tempsStock = temps.get(a);
                    Fourniseur fourniseur=  fourniseurRepository.findById(tempsStock.getIdfourniseur()).get();
                    Produit produit = produitRepository.findById(tempsStock.getIdproduit()).get();
                    Uniter uniter = uniterRepository.findById(tempsStock.getIduniter()).get();
                    Double tva1 = (tempsStock.getPrixunitairevente()-tempsStock.getPrixunitaireachat())*tvaetatRepository.getTva();
                    Double prixttc1= tempsStock.getPrixunitairevente()+tva1;
                    Double total = prixttc1*tempsStock.getQuantiter();

                    tvasomme=tvasomme+tva1;
                    prixttcT=prixttcT+prixttc1;
                    totalp=totalp+total;

                    OverViewMeilleurFourniseur overViewMeilleurFourniseur = new OverViewMeilleurFourniseur(iddemandesociete,fourniseur.getNomfourniseur(),produit.getNomproduit(),uniter.getNomuniter(),tempsStock.getQuantiter(),tempsStock.getPrixunitairevente(),tva1,prixttc1,total,tempsStock.getIdfourniseur());
                    resulta.add(overViewMeilleurFourniseur);
                }
            }
            totalfinale.setTva(tvasomme);
            totalfinale.setPrixttc(prixttcT);
            totalfinale.setPrixtotal(totalp);
            resulta.add(totalfinale);
            return resulta;
    }

    public void faireDemande(Long iddemandesociete,DetailledemandeRepository detailledemandeRepository,UniterRepository uniterRepository,ProduitRepository produitRepository,FourniseurRepository fourniseurRepository,StockRepository stockRepository,TvaetatRepository tvaetatRepository) throws Exception{
            java.util.List<Detailledemande> detailledemande = detailledemandeRepository.listedemande(iddemandesociete);
            StockWork stockWork = new StockWork();
           for(int i=0;i<detailledemande.size();i++){
                Stock stock = new Stock(null, null, detailledemande.get(i).getIdproduit(), null,null,detailledemande.get(i).getQuantiter(), null, null, null, null,iddemandesociete);
                stockWork.demandesocieter(stock, stockRepository);
               
            }
    }
}
