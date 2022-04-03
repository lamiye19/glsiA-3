package com.glsia.tp1.service;

import com.glsia.tp1.models.Approvisionnement;
import com.glsia.tp1.repository.ApprovisionnementRepository;
import com.glsia.tp1.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovisionnementService {

    @Autowired
    private ApprovisionnementRepository approvisionnementRepository;
    @Autowired
    private ProduitRepository produitRepository;

    public void save(Approvisionnement approvisionnement)
    {
        approvisionnementRepository.save(approvisionnement);
    }

    public List<Approvisionnement> showAllApprovisionnement()
    {
        return approvisionnementRepository.findAll();
    }

    public Approvisionnement selectOneApprovisionnement(int id)
    {
        return approvisionnementRepository.findById(id).get();
    }

    /**
     * faire une mise à jour de la quantité du produit après suppression d'un approvisionnement
     * @param id
     */
    public  void deleteApprovisionnement(int id)
    {
        approvisionnementRepository.deleteById(id);
    }

    /**
     *
     * @param id
     * @param quantite
     * faire une mise à jour de la quantité du produit après approvisionnement(ajout ou modification)
     */
    public void updateApprovisionnement(Integer id, int quantite)
    {
        approvisionnementRepository.updateApprovisionnement(id, quantite);
    }

    /**
     *
     * @param id
     * @param quantite
     * faire une mise à jour de la quantité du produit après suppression d'approvisionnement
     *
     */

    public void updateQuantityProductBeforeDeleteApprovisionnement(int id, int quantite)
    {
        approvisionnementRepository.updateQuantityProductBeforeDeleteApprovisionnement(id, quantite);
        //approvisionnementRepository.deleteById(id);
    }

}
