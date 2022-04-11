package com.example.ivan.app.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Approvisionnements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approvisionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantite;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "produitId", insertable = false, updatable = false)
    private Produit produit;

    private Integer produitId;


}
