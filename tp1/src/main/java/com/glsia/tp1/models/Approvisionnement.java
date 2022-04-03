package com.glsia.tp1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Approvisionnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false)
    private int quantite;

    private LocalDate dateCreation;

    private LocalDate dateModification;

    /***
     *  Un approvisionnement concerne un produit
     */
    @ManyToOne
    @JoinColumn(name = "produit_id", insertable = false, updatable = false)
    private Produit produit;
    private int produit_id;

    /***
     *  les approvisionnements sont faits par un user(vendeurs/admin)
     *  @ManyToOne()
     *     @JoinColumn(name = "userId", insertable = false, updatable = false)
     *     private User user;
     *     private int userId;
     */

}
