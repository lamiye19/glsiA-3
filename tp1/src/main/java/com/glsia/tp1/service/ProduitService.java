package com.glsia.tp1.service;

import com.glsia.tp1.models.Produit;
import com.glsia.tp1.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    //Injection de dépendance de repository dans service
    @Autowired
    private ProduitRepository produitRepository;

    //Enregistrer un produit dans la base
    public void saveProduit(Produit produit)
    {
        produitRepository.save(produit);
    }

    //Afficher les produits
    public List<Produit> showAllProduit()
    {
        return produitRepository.findAll();
    }

    //selectionner un seul produit
    public Produit selectedProduit(int id)
    {
        Optional<Produit> optional = produitRepository.findById(id);
        Produit produit = null;
        if(optional.isPresent())
        {
            produit = optional.get();
        }else
        {
            throw new RuntimeException("id introuvable");
        }
        return  produit;
    }

    //supprimer un produit
    public void deleteProduit(int id){
        if (selectedProduit(id) != null) {
            produitRepository.deleteById(id);
        }
    }
    public void updateQuantityProduct(int id, int quantite)
    {
        produitRepository.updateQuantityProduct(id, quantite);
    }

    public void updateQuantityProductAfterSale(int id, int quantite)
    {
        produitRepository.updateQuantityProductAfterSale(id, quantite);
    }

}
