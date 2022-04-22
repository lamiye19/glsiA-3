package com.example.ivan.app.services;

import com.example.ivan.app.models.Categorie;
import com.example.ivan.app.models.Produit;
import com.example.ivan.app.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public void Add(Categorie categorie){
        categorieRepository.save(categorie);
    }

    public List<Categorie> getList(){
        return categorieRepository.findAll();
    }

    public Categorie selectOne(Integer id){
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        Categorie categorie = null;
        if(categorieOptional.isPresent()){
            categorie = categorieOptional.get();
        } else{
            throw new RuntimeException("Aucune catégorie trouvé ayant id = "+ id);
        }
        return categorie;
    }

    public void delete(Integer id){
        if(selectOne(id) != null){
            categorieRepository.deleteById(id);
        }
    }
}
