package com.work.varotra.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class SocieteFront {

    @GetMapping("/societefront/demande")
    public String demande(Model model){
        return "Societe/Demande";
    }
    @GetMapping("/societefront/meilleurfourniseur")
    public String meilleurfourniseur(Model model){
        return "Societe/MeilleurFourniseur";
    }
    @GetMapping("/societefront/proformat")
    public String proformat(Model model){
        return "Societe/Proformat";
    }
    @GetMapping("/societefront/demandesub")
    public String demandesub(Model model){
        return "Societe/DemandeSub";
    }
    @GetMapping("/societefront/detailledemande")
    public String detailledemande(Model model){
        return "Societe/DetailleDemande";
    }

    @GetMapping("/societefront/accuille")
    public String accuille(Model model){
        return "Societe/Accuille";
    }


}
