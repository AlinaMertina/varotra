package com.work.varotra.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class FourniseurFront {

    @GetMapping("/fourniseur/produit")
    public String produit(Model model){
        return "Fourniseur/Produit";
    }

    @GetMapping("/fourniseur/detailleproduit")
    public String detailleproduit(Model model){
        return "Fourniseur/DetailleProduit";
    }

    @GetMapping("/fourniseur/entrerstock")
    public String entrerstock(Model model){
        return "Fourniseur/EntrerStock";
    }

    @GetMapping("/fourniseur/demandeclient")
    public String demandeclient(Model model){
        return "Fourniseur/DemandeClient";
    }

    @GetMapping("/fourniseur/detailledemandeclient")
    public String detailledemandeclient(Model model){
        return "Fourniseur/DetailleDemandeClient";
    }

    @GetMapping("/fourniseur/accuille")
    public String accuille(Model model){
        return "Fourniseur/Accuille";
    }
   
}
