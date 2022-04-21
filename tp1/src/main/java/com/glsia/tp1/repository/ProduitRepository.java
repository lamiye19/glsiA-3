package com.glsia.tp1.repository;

import com.glsia.tp1.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer > {

    @Query(value = "UPDATE produits set qte_stok = qte_stok+quantite where id = ?", nativeQuery = true)
    void updateQuantityProduct(int id, int quantite);
}
