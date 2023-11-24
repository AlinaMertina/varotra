package com.work.varotra.Service;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.work.varotra.Entity.Demandesociete;
import com.work.varotra.Entity.Detailledemande;
import com.work.varotra.Repository.DemandesocieteRepository;
import com.work.varotra.Repository.DetailledemandeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandeService {
    private final DemandesocieteRepository demandesocieteRepository;
    private final DetailledemandeRepository detailledemandeRepository;

    public void insertDemandeSociete(String[] idproduit,String[] iduniter,String[] quantiter,String datedemande,String datelimite,Long idemploye,Long client)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Demandesociete demandesociete = new Demandesociete(null, null, format.parse(datedemande), format.parse(datelimite), idemploye);
        demandesocieteRepository.save(demandesociete);
        for(int i=0;i<idproduit.length;i++){
            Detailledemande detailledemande = new Detailledemande(null, demandesociete.getIddemandesociete(), Long.parseLong(idproduit[i]), client, Long.parseLong(iduniter[i]), Double.parseDouble(quantiter[i]));
            detailledemandeRepository.save(detailledemande);
        }
    }
}
