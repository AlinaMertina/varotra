package com.work.varotra.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.varotra.Entity.Demandesociete;
import com.work.varotra.Entity.Employe;
import com.work.varotra.Repository.ClientRepository;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.DetailledemandeRepository;
import com.work.varotra.Repository.EmployeRepositor;
import com.work.varotra.Repository.FourniseurRepository;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.StockRepository;
import com.work.varotra.Repository.TvaetatRepository;
import com.work.varotra.Repository.TypedepayementRepository;
import com.work.varotra.Repository.UniterRepository;
import com.work.varotra.Service.SocieteService;
import com.work.varotra.Work.OverViewFacture;
import com.work.varotra.Work.OverViewListeFacture;
import com.work.varotra.Work.OverViewMeilleurFourniseur;
import com.work.varotra.Work.OverViewProformat;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.work.varotra.Service.DemandeService;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
public class SocieteFront {
    private final UniterRepository uniterRepository;
    private final ProduitRepository produitRepository;
    private final SocieteService societeService;
    private final EmployeRepositor employe;
    private final DemandeService demandeService;
    private final DemandesocieteRepository demandesocieteRepository;
    private final DetailledemandeRepository detailledemandeRepository;
    private final TvaetatRepository tvaetatRepository;
    private final FourniseurRepository fourniseurRepository;
    private final StockRepository stockRepository;
    private final TypedepayementRepository typedepayementRepository;
    private final ClientRepository clientRepository;

   
    @GetMapping("/societefront/demande")
    public String demande(Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        if(employe1.getIdsupe()==null){
            model.addAttribute("error", "seule les subaltaire peuvent fair une demande");
            model.addAttribute("backlink", "/societefront/accuille");
            return "Societe/Error";
        }
        else  if(employe.findById(employe1.getIdsupe()).get().getIdsupe()!=null){
            model.addAttribute("listeuniter", uniterRepository.findAll());
            model.addAttribute("listeproduit", produitRepository.findAll());
            return "Societe/Demande";
        }else{
            model.addAttribute("erreu", "seule les subaltaire peut fair une demande");
            model.addAttribute("backlink", "/societefront/accuille");
            return "Societe/Error";
        }
    }
     @GetMapping("/societefront/liste")
    public String listefacture(Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        if(employe1.getIdsupe()==null){
            model.addAttribute("listedemande", new OverViewListeFacture().getlistefacture(employe1.getIdemploye(), stockRepository, demandesocieteRepository, fourniseurRepository));
            return "Societe/ListeFacture";
        }else{
            model.addAttribute("erreu", "seul les superieur peuvent voir les facture");
            model.addAttribute("backlink", "/societefront/accuille");
            return "Societe/Error";
        }
    }

    @GetMapping("/societefront/facture")
    public String facture(@RequestParam("idfourniseur") Long idfourniseur , @RequestParam("iddemandesociete") Long iddemandesociete,Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        String numerodefacture="F0"+idfourniseur+"0"+employe1.getIdclient()+"0"+iddemandesociete;
        
        Demandesociete demandesociete = demandesocieteRepository.findById(iddemandesociete).get();
        String numerodecommande = "B0"+demandesociete.getIddemandesociete()+"0"+employe1.getIddepartement()+"02";
        model.addAttribute("numerodecommande",numerodecommande);
        model.addAttribute("typepayement",typedepayementRepository.findById(demandesociete.getIdtypedepayement()).get());
        model.addAttribute("numerofacture",numerodefacture);
        model.addAttribute("demandesociete",demandesociete);
        model.addAttribute("infoclient",clientRepository.findById(employe1.getIdclient()).get());
        model.addAttribute("infofourniseur", fourniseurRepository.findById(idfourniseur).get());
        model.addAttribute("detaillefacture", new OverViewFacture().detailleFacture(idfourniseur, iddemandesociete, stockRepository, tvaetatRepository));
        return "Societe/Facture";
    }

    @GetMapping("/societefront/meilleurfourniseur")
    public String meilleurfourniseur(Model model){
        return "Societe/MeilleurFourniseur";
    }

    @GetMapping("/societefront/proformat")
    public String proformat(@RequestParam("iddemandesociete")  Long iddemandesociete,@RequestParam("idfourniseur")  Long idfourniseur,Model model){
        model.addAttribute("prixproduit", new OverViewProformat().getProformatsFourniseur(iddemandesociete, idfourniseur, stockRepository, detailledemandeRepository, tvaetatRepository) );
        return "Societe/Proformat";
    }

    @GetMapping("/societefront/demandesub")
    public String demandesub(Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        model.addAttribute("listedemande", societeService.listedemande(employe1.getIdemploye()));
        return "Societe/DemandeSub";
    }
    @GetMapping("/societefront/detailledemande")
    public String detailledemande(@RequestParam("iddemandesociete")  Long iddemandesociete,Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        System.out.println("idsuperieur "+employe1.getIdsupe());
        if(employe1.getIdsupe()==null){
            try {
                model.addAttribute("iddemandesociete", iddemandesociete);
                model.addAttribute("meilleur", new OverViewMeilleurFourniseur().listeMeilleurFourniseur(iddemandesociete, detailledemandeRepository, uniterRepository, produitRepository, fourniseurRepository, stockRepository, tvaetatRepository));
                model.addAttribute("typepayement",typedepayementRepository.findAll());
            } catch (Exception e) {
                System.out.println(e);
            }
            return "Societe/MeilleurFourniseur";
        }
        else  if(employe.findById(employe1.getIdsupe()).get().getIdsupe()==null){
            model.addAttribute("detailledemande", societeService.detailledemande(iddemandesociete));
            return "Societe/DetailleDemande";
        }else{
            model.addAttribute("erreu", "seule les superieur peuvent voir");
            model.addAttribute("backlink", "/societefront/accuille");
            return "Societe/Error";
        }

        //detaille demande sup1 
    }

    @PostMapping("/societefront/validedemandesup1")
    public String validedemandesup1(@RequestParam("iddemandesociete")  String iddemandesociete,@RequestParam("typepayement") String  typepayement,Model model,HttpSession session){
        Employe employe1 = (Employe) session.getAttribute("employe");
        Demandesociete demandesociete = demandesocieteRepository.findById( Long.parseLong(iddemandesociete)).get();
        demandesociete.setValidationsup(employe1.getIdemploye());
        
        if(employe1.getIdsupe()==null){
            try {
                demandesociete.setIdtypedepayement(Long.parseLong(typepayement));
                new OverViewMeilleurFourniseur().faireDemande(Long.parseLong(iddemandesociete), detailledemandeRepository, uniterRepository, produitRepository, fourniseurRepository, stockRepository, tvaetatRepository);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        demandesocieteRepository.save(demandesociete);
        return this.demandesub(model,session);
    }

    @GetMapping("/societefront/accuille")
    public String accuille(Model model){
        return "Societe/Accuille";
    }

    @GetMapping("/societefront/login")
    public String login(Model model){
        return "Societe/Login";
    }

    @PostMapping("/societefront/checkconnexion")
    public String checkconnexion(@RequestParam("idemploye")  String idemploye,@RequestParam("password") String  password,HttpSession session){
            try {
                 System.out.println("welcome to check");
                societeService.connexion(Long.parseLong(idemploye), password);
                Employe employeva = (Employe) employe.findById(Long.parseLong(idemploye)).get();
                session.setAttribute("employe",employeva);
                if(employeva.getIdfourniseur()!=null){
                    return "Fourniseur/Accuille";
                }else{
                    return "Societe/Accuille";
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "Societe/Login";
            }
    }

    

    @PostMapping("/societe/insertdemandeclient")
    public String insertdemandeclient(@RequestParam("idproduit[]") String[] idproduit,@RequestParam("idunitee[]") String[] iduniter,@RequestParam("quantiter[]") String[] quantiter,@RequestParam("datedemande") String datedemande,@RequestParam("datelimite") String datelimite,HttpSession session){
        try {
            Employe employe = (Employe) session.getAttribute("employe");
            demandeService.insertDemandeSociete(idproduit,iduniter,quantiter,datedemande,datelimite,employe.getIdemploye(),employe.getIdclient());
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Societe/Demande";
    }


}
