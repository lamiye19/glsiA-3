package com.glsia.tp1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false,unique = true, length = 50)
    private String libelle;

    private int qteStok;
    @Column(nullable = false)
    private int qteSeuil;

    @Column(nullable = false)
    private double prix;

    private LocalDate dateCreation;

    /**
     * Plusieurs produits pour une même catégorie
     */
    @ManyToOne()
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;
    private int categoryId;

    /***
     *  Un produit pour plusieurs ligne de ventes (bill = facture)
     */
    /*@OneToMany(mappedBy = "produit")
    private List<Bill> bills;*/
}
