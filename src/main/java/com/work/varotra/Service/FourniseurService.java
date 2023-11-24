package com.work.varotra.Service;

import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Work.OverViewProduit;
import com.work.varotra.Work.StockWork;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.work.varotra.Entity.Produit;
import com.work.varotra.Entity.Stock;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FourniseurService {

    private final StockRepository stockRepository;
    private final ProduitRepository produitRepository;
    

    public void save(Stock stock) throws Exception{
        Produit produit = produitRepository.findById(stock.getIdproduit()).get();
        if(produit!=null){
            if(stock.getIduniter()!=produit.getIduniter()){
                throw new Exception("uniter du produit ne coresponde pas");
            }
        }
        new StockWork().save(stock,stockRepository);
        
    }
    public java.util.List<OverViewProduit> listeproduit(Long idfournieur){
        return new OverViewProduit().getlisteOverViewProduit(idfournieur, stockRepository);
    }

    public  java.util.List<Stock>  stortieMutliFourniseur(Long idproduit,Double quantier) throws Exception{
        Stock stock = new Stock(null, null, idproduit, null, null, quantier, null, null, null, null,null);
        return  new StockWork().stortieMutliFourniseur(stock,stockRepository);
    }

    public java.util.List<java.util.List<Stock>> stortieMutliFourniseurMultiProduit(String[] idproduit,String[] quantier) throws Exception{
        java.util.List<java.util.List<Stock>> resulta = new ArrayList<java.util.List<Stock>>();
            for(int i=0;i<idproduit.length;i++){
                resulta.add(this.stortieMutliFourniseur(Long.parseLong(idproduit[i]),Double.parseDouble(quantier[i])));
            }
        return resulta;
    }

    
}
