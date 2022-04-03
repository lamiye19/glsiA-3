package com.glsia.tp1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String phone;

    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt = LocalDate.now();

    /***
     *  Un client peut faire plusieurs achats(vente)
     */

    /*@OneToMany(mappedBy = "customer")
    private List<Sale> sales;*/


}
