package com.work.varotra.Service;

import org.springframework.stereotype.Service;

import com.work.varotra.Repository.ConnexionRepository;
import com.work.varotra.Repository.EmployeRepositor;
import com.work.varotra.Repository.ProduitRepository;
import com.work.varotra.Repository.UniterRepository;
import com.work.varotra.Work.OverViewDemandeDetaille;
import com.work.varotra.Work.OverViewDemandeSociete;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.DetailledemandeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocieteService {
     private final ConnexionRepository connexionRepository;
     private final DemandesocieteRepository demandesocieteRepository;
     private final EmployeRepositor employeRepositor;
     private final ProduitRepository produitRepository;
     private final DetailledemandeRepository detailledemandeRepository;
     private final UniterRepository uniterRepository;


     public void connexion(Long idemploye,String password) throws Exception{
            if(connexionRepository.findByLogin(idemploye,password).get()==null){
                throw new Exception("info de connexion n'est pas valide");
            }
     }
    public java.util.List<OverViewDemandeSociete> listedemande(Long idsupe){
            return new OverViewDemandeSociete().listedemande(idsupe,demandesocieteRepository,employeRepositor);
    }
    public java.util.List<OverViewDemandeDetaille> detailledemande(Long iddemandesocieter){
        return new OverViewDemandeDetaille().listedetailledemande(iddemandesocieter, detailledemandeRepository, produitRepository,uniterRepository);
    }


}
