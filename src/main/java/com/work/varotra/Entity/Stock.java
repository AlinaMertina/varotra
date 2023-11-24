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
public class Stock {

     @Id
    @SequenceGenerator(
        name = "stock_sequence",
        sequenceName = "stock_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "stock_sequence"
    )
    Long idstock ;
    Long idfourniseur ;
    Long idproduit ;
    Long idaction ;
    Long iduniter ;
    Double quantiter ;
    Double prixunitaireachat;
    Double prixunitairevente ;
    Date date;
    Long idfstock;
    Long iddemandesociete;
}
