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
public class Employe {



    @Id
    @SequenceGenerator(
        name = "employe_sequence",
        sequenceName = "employe_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employe_sequence"
    )
    Long  idemploye ;
    String nomemployer ;
    Long idsupe ;
    Long idclient;
    Long iddepartement;
    Long idfourniseur;
}
