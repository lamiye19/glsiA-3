package com.glsia.tp1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(nullable = false, length = 50)
    private String libelle;

    private int qteStok;
    @Column(nullable = false)
    private int qteSeuil;

    @Column(nullable = false)
    private double prix;

    private LocalDate dateCreation;

    @ManyToOne()
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    private int categoryId;
}
