package com.glsia.tp1.repository;

import com.glsia.tp1.models.Approvisionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement,Integer> {

    /**
     *
     * @param id
     * @param quantite
     * faire une mise à jour de la quantité du produit après approvisionnement(ajout ou modification)
     */

    @Modifying
    @Transactional
    @Query(value = "update Approvisionnement a set a.quantite = :quantite where a.id = :id")
    void updateApprovisionnement(@Param("id") Integer id, @Param("quantite") int quantite);

    /**
     * Faire une mise à jour de la quantité du stock après suppression d'un approvisionnement
     * @Param id
     * @Param quantite
     */
    @Modifying
    @Transactional
    @Query(value = "update Produit p set p.qteStok = p.qteStok - :quantite where p.id = :id")
    void updateQuantityProductBeforeDeleteApprovisionnement(@Param("id") Integer id, @Param("quantite") int quantite);

}
