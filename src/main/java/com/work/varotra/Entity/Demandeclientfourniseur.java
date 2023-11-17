package com.work.varotra.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Demandeclientfourniseur {

    @Id
    @SequenceGenerator(
        name = "demandeclient_sequence",
        sequenceName = "demandeclient_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "demandeclient_sequence"
    )
    Long iddemandeclient ;
    Long idproduit ;
    Long idclient;
    Long idfourniseur ;
    Long iduniter;
    Double quantiter ;
    Date datedemande ;
    Date datefindemande ;
    
} 