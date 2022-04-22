package com.example.ivan.app.repositories;

import com.example.ivan.app.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository <Produit, Integer> {

}
