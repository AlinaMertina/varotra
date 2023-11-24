package com.work.varotra.Work;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Work.OverViewProduit;

import java.util.Date;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.work.varotra.Entity.Stock;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// @Data
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
public class StockWork {

    

    public void save(Stock stock,StockRepository stockRepository) throws Exception{//entrer plus invantaire
        stockRepository.save(stock);
        java.util.List<Stock> listestock = stockRepository.inventaireProduitFourniseur(stock.getIdfourniseur(), stock.getIdproduit());
        //faire inventaire
        for(int i =0;i<listestock.size();i++){
                System.out.println("idstock "+ listestock.get(i).getIdfstock());
                Stock stockminim = listestock.get(i);  
                stockRepository.save(stockminim );
        }
        Stock tempsStock = new Stock(null, stock.getIdfourniseur(), stock.getIdproduit(), 3L, stock.getIduniter(), stock.getQuantiter(), stock.getPrixunitaireachat(), stock.getPrixunitairevente(), new Date(), stock.getIdstock(),null);
        stockRepository.save(tempsStock);
    }
    public java.util.List<java.util.List<Stock>> inventaireMultiFourniseur(Stock stock,StockRepository stockRepository){
        java.util.List<Long> listefourniseur = stockRepository.ensemblefourniseur();
        java.util.List<java.util.List<Stock>> listestock = new java.util.ArrayList<java.util.List<Stock>>();
        for(int i=0;i<listefourniseur.size();i++){
            java.util.List<Stock> tempsListestock = stockRepository.inventaireProduitFourniseur(listefourniseur.get(i), stock.getIdproduit());
            System.out.println("size produit foruniseur"+listefourniseur.get(i)+" "+tempsListestock.size());
            if(tempsListestock.size()>0){
                listestock.add(tempsListestock);
            }
        }
        return listestock;
    }
    public java.util.List<Stock> stortieMutliFourniseur(Stock stock,StockRepository stockRepository) throws Exception{
        java.util.List<Stock> resulta = new java.util.ArrayList<Stock>();
        while (stock.getQuantiter()>0) {
            java.util.List<java.util.List<Stock>> listestock =this.inventaireMultiFourniseur(stock, stockRepository);
            System.out.println("size fourniseur"+ listestock.size());
            if(listestock.size()<=0){
                break;
            }
            Stock stockminim = listestock.get(0).get(0);
            for(int i=0;i<listestock.size();i++){//get prix minimum
                java.util.List<Stock> listeStockFourniseur= listestock.get(i); //stock de chaque fourniseur
                if(stockminim.getPrixunitairevente()>
                listeStockFourniseur.get(0).getPrixunitairevente()){
                    stockminim=listeStockFourniseur.get(0);
                }
            }
            //faire la sortie
            Double quantier = stock.getQuantiter();
            if(quantier >= stockminim.getQuantiter()){
                quantier=quantier-stockminim.getQuantiter();
                stock.setQuantiter(quantier);
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 2L, stockminim.getIduniter(), stockminim.getQuantiter(), stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),null);
                resulta.add(stocktemps);
                this.sortie(stocktemps, stockRepository);
            }else{
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 2L, stockminim.getIduniter(),quantier, stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),null);
                resulta.add(stocktemps);
                this.sortie(stocktemps, stockRepository);
                stock.setQuantiter(0.0);
                
            }
        }
        return resulta;
    }
    public void sortie(Stock stock,StockRepository stockRepository)throws Exception{ //rehefa magnala izi eto de ligne par ligne
        stockRepository.save(stock);
        java.util.List<Stock> listestock = stockRepository.inventaireProduitFourniseur(stock.getIdfourniseur(), stock.getIdproduit());
        if(listestock.size()>0){
            System.out.println("quantiter reste"+ (listestock.get(0).getQuantiter()-stock.getQuantiter()));
            listestock.get(0).setQuantiter(listestock.get(0).getQuantiter()-stock.getQuantiter());
        }
        //faire inventaire
        for(int i =0;i<listestock.size();i++){
                Stock stocktemps = listestock.get(i);  
                stocktemps.setDate(new Date());
                stockRepository.save(stocktemps);
        }
    }


    public java.util.List<Stock> detailleMutliFourniseur(Stock stock,StockRepository stockRepository) throws Exception{
        java.util.List<Stock> resulta = new java.util.ArrayList<Stock>();
        java.util.List<java.util.List<Stock>> listestock =this.inventaireMultiFourniseur(stock, stockRepository);
        while (stock.getQuantiter()>0) {
            System.out.println("size fourniseur"+ listestock.size());
            if(listestock.size()<=0){
                break;
            }
            Stock stockminim = listestock.get(0).get(0);
            int indexmin=0;
            for(int i=0;i<listestock.size();i++){//get prix minimum
                java.util.List<Stock> listeStockFourniseur= listestock.get(i); //stock de chaque fourniseur
                if(stockminim.getPrixunitairevente()>
                listeStockFourniseur.get(0).getPrixunitairevente()){
                    stockminim=listeStockFourniseur.get(0);
                    indexmin=i;
                }
            }
            //faire la sortie
            Double quantier = stock.getQuantiter();
            if(quantier >= stockminim.getQuantiter()){
                quantier=quantier-stockminim.getQuantiter();
                stock.setQuantiter(quantier);
                listestock.get(indexmin).remove(0);
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 2L, stockminim.getIduniter(), stockminim.getQuantiter(), stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),null);
                resulta.add(stocktemps);
            }else{
               
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 2L, stockminim.getIduniter(),quantier, stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),null);
                resulta.add(stocktemps);
                stock.setQuantiter(0.0);
                
            }
        }
        return resulta;
    }

    public void demandesocieter(Stock stock,StockRepository stockRepository) throws Exception{
        java.util.List<java.util.List<Stock>> listestock =this.inventaireMultiFourniseur(stock, stockRepository);
        while (stock.getQuantiter()>0) {
            System.out.println("size fourniseur"+ listestock.size());
            if(listestock.size()<=0){
                break;
            }
            Stock stockminim = listestock.get(0).get(0);
            int indexmin=0;
            for(int i=0;i<listestock.size();i++){//get prix minimum
                java.util.List<Stock> listeStockFourniseur= listestock.get(i); //stock de chaque fourniseur
                if(stockminim.getPrixunitairevente()>
                listeStockFourniseur.get(0).getPrixunitairevente()){
                    stockminim=listeStockFourniseur.get(0);
                    indexmin=i;
                }
            }
            //faire la sortie
            Double quantier = stock.getQuantiter();
            if(quantier >= stockminim.getQuantiter()){
                quantier=quantier-stockminim.getQuantiter();
                stock.setQuantiter(quantier);
                listestock.get(indexmin).remove(0);
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 4L, stockminim.getIduniter(), stockminim.getQuantiter(), stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),stock.getIddemandesociete());
                stockRepository.save(stocktemps);
            }else{
               
                Stock stocktemps = new Stock(null, stockminim.getIdfourniseur(), stock.getIdproduit(), 4L, stockminim.getIduniter(),quantier, stockminim.getPrixunitaireachat(), stockminim.getPrixunitairevente(), stockminim.getDate(), stockminim.getIdfstock(),stock.getIddemandesociete());
                stock.setQuantiter(0.0);
                stockRepository.save(stocktemps);
            }
        }
    }

    public void validerdemande(Long[] idstock,StockRepository stockRepository){
        System.out.println("valider demande");
        for(int i=0;i<idstock.length;i++){
            System.out.println("numero de stock"+idstock[i]);
            Stock stock = stockRepository.findById(idstock[i]).get();
            java.util.List<Stock> inventaireProduit = stockRepository.inventaireDemande(stock.getIdfstock());
            inventaireProduit.get(1).setIdaction(2L);
            inventaireProduit.get(0).setQuantiter(inventaireProduit.get(1).getQuantiter() - inventaireProduit.get(0).getQuantiter());
            stockRepository.save(inventaireProduit.get(1));
            stockRepository.save(inventaireProduit.get(0));
        }
    }

    
}
