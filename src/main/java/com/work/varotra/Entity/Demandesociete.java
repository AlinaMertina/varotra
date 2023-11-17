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
public class Demandesociete {

    @Id
    @SequenceGenerator(
        name = "demandesociete_sequence",
        sequenceName = "demandesociete_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "demandesociete_sequence"
    )
    Long iddemandesociete ;
    Long idtypedepayement ;
    Date datedemande ;
    Date delait ;
    Long validationsup;
}
