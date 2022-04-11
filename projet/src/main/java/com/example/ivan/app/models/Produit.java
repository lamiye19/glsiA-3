package com.example.ivan.app.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Produits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String designation;

    @NotNull
    private double prix;

    @NotNull
    private Integer quantiteSeuil;

    @NotNull
    private Integer quantiteStock;

    @NotNull
    private LocalDate dateCreation;

    @ManyToOne()
    @JoinColumn(name = "categorieId", insertable = false, updatable = false)
    private Categorie categorie;

    private Integer categorieId;
}