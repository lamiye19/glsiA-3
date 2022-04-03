package com.glsia.tp1.repository;

import com.glsia.tp1.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer > {

    /**
     *
     * @param id
     * @param quantite
     * faire une mise à jour de la quantité du produit après approvisionnement(ajout ou modification)
     */
    @Modifying
    @Transactional
    @Query(value = "update Produit p set p.qteStok = p.qteStok + :quantite where p.id = :id")
    void updateQuantityProduct(@Param("id") Integer id, @Param("quantite") int quantite);


    /**
     *
     * @param id
     * @param quantite
     * faire une mise à jour de la quantité du produit après une vente(vente ou modification)
     */
    @Modifying
    @Transactional
    @Query(value = "update Produit p set p.qteStok = p.qteStok - :quantite where p.id = :id")
    void updateQuantityProductAfterSale(@Param("id") Integer id, @Param("quantite") int quantite);

    @Modifying
    @Transactional
    @Query(value = "update Produit p set p.qteStok = p.qteStok - :quantite where p.id = :id")
    void updateQuantityProductAfterDelete(@Param("id") Integer id, @Param("quantite") int quantite);


}
