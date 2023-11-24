package com.work.varotra.Work;

import com.work.varotra.Entity.Demandesociete;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.EmployeRepositor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverViewDemandeSociete {
    Long iddemandesociete;
    String nomDepartement;
    java.util.Date dateCommande;
    java.util.Date delai;

    public java.util.List<OverViewDemandeSociete> listedemande(Long idsupe,DemandesocieteRepository demandesocieteRepository,EmployeRepositor employeRepositor){
            java.util.List<Demandesociete> liste = demandesocieteRepository.listedemande(idsupe);
            java.util.List<OverViewDemandeSociete> resulta = new java.util.ArrayList<OverViewDemandeSociete>();
            for(int i=0;i<liste.size();i++){
                OverViewDemandeSociete overViewDemandeSociete = new OverViewDemandeSociete( liste.get(i).getIddemandesociete(),employeRepositor.nomDepartement(liste.get(i).getValidationsup()),liste.get(i).getDatedemande(), liste.get(i).getDelait());
                resulta.add(overViewDemandeSociete);
            }
            return resulta;
    }
}
