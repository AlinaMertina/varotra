package com.work.varotra.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class Typedepayement {
    @Id
    @SequenceGenerator(
        name = "typedepayement_sequence",
        sequenceName = "typedepayement_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "typedepayement_sequence"
    )
    Long idtypedepayement ;
    String nompayement ;
}
