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
public class Detaillestock {

    @Id
    @SequenceGenerator(
        name = "detaillestock_sequence",
        sequenceName = "detaillestock_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "detaillestock_sequence"
    )
    Long iddetaillestock ;
    Long idstock ;
    Long idclient ;
    Date datedelivraison ;
    Long idtypedepayement ;
}
