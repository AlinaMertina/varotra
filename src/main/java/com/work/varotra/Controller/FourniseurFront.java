package com.work.varotra.Controller;

import java.text.SimpleDateFormat;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.varotra.Entity.Detailledemande;
import com.work.varotra.Entity.Employe;
import com.work.varotra.Entity.Stock;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Repository.TvaetatRepository;
import com.work.varotra.Repository.UniterRepository;
import com.work.varotra.Repository.DetailledemandeRepository;
import com.work.varotra.Repository.FourniseurRepository;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Service.FourniseurService;
import com.work.varotra.Work.OverViewDemandeFourniseur;
import com.work.varotra.Work.OverViewMeilleurFourniseur;
import com.work.varotra.Work.StockWork;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class FourniseurFront {
    private final UniterRepository uniterRepository;
    private final ProduitRepository produitRepository;
    private final StockRepository stockRepository;
    private final FourniseurService stockService;
    private final DetailledemandeRepository detailledemandeRepository;
    private final TvaetatRepository tvaetatRepository;
    private final FourniseurRepository fourniseurRepository;
    private final DemandesocieteRepository demandesocieteRepository;

   
    @GetMapping("/fourniseur/produit")
    public String produit(Model model,HttpSession session){
        Employe employe = (Employe) session.getAttribute("employe");
        model.addAttribute("listeuniter", uniterRepository.findAll());
        model.addAttribute("listeproduit", stockService.listeproduit(employe.getIdfourniseur()));
        return "Fourniseur/Produit";
    }

    @GetMapping("/fourniseur/detailleproduit")
    public String detailleproduit(@RequestParam("idproduit")Long idproduit,Model model){
        model.addAttribute("listedetaille", stockRepository.listetsockproduit(idproduit));
        return "Fourniseur/DetailleProduit";
    }

    @GetMapping("/fourniseur/entrerstock")
    public String entrerstock(Model model){
        model.addAttribute("listeuniter", uniterRepository.findAll());
        model.addAttribute("listeproduit", produitRepository.findAll());
        return "Fourniseur/EntrerStock";
    }

    @GetMapping("/fourniseur/demandeclient")
    public String demandeclient(Model model,HttpSession session){
        Employe employe = (Employe) session.getAttribute("employe");
        model.addAttribute("listedemande", new OverViewDemandeFourniseur().getDemandeClient(employe.getIdfourniseur(),stockRepository,demandesocieteRepository,tvaetatRepository));
        return "Fourniseur/DemandeClient";
    }

    @GetMapping("/fourniseur/valider")
    public String validerdemande(@RequestParam("idstock[]") Long[] idstock,Model model,HttpSession session){
        try {
            System.out.println("liste stock "+idstock[0]);
            new StockWork().validerdemande(idstock,stockRepository);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.demandeclient(model,session);
    }


    @GetMapping("/fourniseur/detailledemandeclient")
    public String detailledemandeclient(Model model){
        return "Fourniseur/DetailleDemandeClient";
    }
    // validerdemande(Long[] idstock,StockRepository stockRepository)
    @GetMapping("/fourniseur/accuille")
    public String accuille(Model model){
        return "Fourniseur/Accuille";
    }

    @PostMapping("/fourniseur/stock/save")
    public String save(@RequestParam("idproduit")String idproduit,@RequestParam("iduniter")String iduniter,@RequestParam("quantiter")String quantiter,@RequestParam("prixunitaireachat")String prixunitaireachat,@RequestParam("prixunitairevente")String prixunitairevente,@RequestParam("date")String date,HttpSession session,Model model){
        try {
            Employe employe = (Employe) session.getAttribute("employe");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Stock stock = new Stock(null,employe.getIdfourniseur(),Long.parseLong(idproduit),1L, Long.parseLong(iduniter), Double.parseDouble(quantiter),Double.parseDouble(prixunitaireachat),Double.parseDouble(prixunitairevente), format.parse(date),null,null);
            stockService.save(stock);
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("erreur", e.getMessage());
            return this.entrerstock(model);
        }
        model.addAttribute("erreur", null);
        return this.entrerstock(model);
    }

    @PostMapping("/fourniseur/meilleurforniseur")
    public String sortieMultiFourniseur(@RequestParam("iddemandesociete")Long iddemandesociete,Model model){
        try {
            model.addAttribute("meilleur", new OverViewMeilleurFourniseur().listeMeilleurFourniseur(iddemandesociete, detailledemandeRepository, uniterRepository, produitRepository, fourniseurRepository, stockRepository, tvaetatRepository));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Societe/MeilleurFourniseur";
    }
    

   
}
