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


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reponsefourniseur {

    @Id
    @SequenceGenerator(
        name = "reponsefourniseur_sequence",
        sequenceName = "reponsefourniseur_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "reponsefourniseur_sequence"
    )
    Long idreponse ;
    Long iddemandesociete ;
    Long idfourniseur ;
    Long iduniter ;
    Double prixunitaireachat ;
    Double tva ;
    Double quantiterdisponible ;
}
