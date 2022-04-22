package com.example.ivan.app.services;

import com.example.ivan.app.models.Produit;
import com.example.ivan.app.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;


    public void Add(Produit produit){
        produitRepository.save(produit);
    }

    public List<Produit> getList(){
        return produitRepository.findAll();
    }

    public Produit selectOne(Integer id){
        Optional<Produit> produitOptional = produitRepository.findById(id);
        Produit produit = null;
        if(produitOptional.isPresent()){
            produit = produitOptional.get();
        } else{
            throw new RuntimeException("Aucun produit trouvé ayant id = "+ id);
        }
        return produit;
    }

    public void delete(Integer id){
        if(selectOne(id) != null){
            produitRepository.deleteById(id);
        }
    }

    /*
    public void update(Produit produit, Integer id){
        if(selectOne(id) != null){
            Produit p = null;
            p.setId(produit.getId());
            p.setDesignation(produit.getDesignation());
            p.setPrix(produit.getPrix());
            p.setQuantiteSeuil(produit.getQuantiteSeuil());
            p.setQuantiteStock(produit.getQuantiteStock());
            p.setDateCreation(produit.getDateCreation());
            produitRepository.save(p);
        }
    }
    */

}
