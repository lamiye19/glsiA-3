package com.glsia.tp1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int qty;
    private int salePrice;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Produit product;
    private int productId;

    @ManyToOne
    @JoinColumn(name = "billId", insertable = false, updatable = false)
    private Bill bill;
    private int billId;
}
