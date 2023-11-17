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
public class Fourniseur {
    
    @Id
    @SequenceGenerator(
        name = "fourniseur_sequence",
        sequenceName = "fourniseur_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "fourniseur_sequence"
    )
    Long idfourniseur ;
    String nomfourniseur ;
    String numerofournieur;
    
}
