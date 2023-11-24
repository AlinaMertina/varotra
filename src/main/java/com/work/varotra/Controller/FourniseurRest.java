package com.work.varotra.Controller;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.work.varotra.Service.FourniseurService;
import com.work.varotra.Work.OverViewProformat;

import lombok.RequiredArgsConstructor;
import com.work.varotra.Entity.Stock;
import com.work.varotra.Repository.DetailledemandeRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Repository.TvaetatRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FourniseurRest {
    private final FourniseurService stockService;
    private final StockRepository stockRepository;
    private final DetailledemandeRepository detailledemandeRepository;
    private final TvaetatRepository tvaetatRepository;

    @PostMapping("/fourniseur/sortiemultifourniseur")
    public java.util.List<Stock> sortieMultiFourniseur(@RequestParam("idproduit")String idproduit,@RequestParam("quantiter")String quantiter){
        try {
            return stockService.stortieMutliFourniseur(Long.parseLong(idproduit),Double.parseDouble(quantiter));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/societefront/proformatrest")
    public java.util.List<OverViewProformat> proformat(@RequestParam("iddemandesociete")  Long iddemandesociete,@RequestParam("idfourniseur")  Long idfourniseur){
        try {
            return new OverViewProformat().getProformatsFourniseur(iddemandesociete, idfourniseur, stockRepository, detailledemandeRepository, tvaetatRepository);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
