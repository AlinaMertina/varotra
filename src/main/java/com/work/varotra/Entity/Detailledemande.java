package com.work.varotra.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detailledemande {

    @Id
    @SequenceGenerator(
        name = "detailledemande_sequence",
        sequenceName = "detailledemande_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "detailledemande_sequence"
    )
    Long iddetailledemande ;
    Long iddemandesociete ;
    Long idproduit ;
    Long idclient ;
    Long iduniter ;
    Double quantiter ;
    Double datedemande ;
}
