package com.example.ivan.app.controllers;

import com.example.ivan.app.models.Categorie;
import com.example.ivan.app.models.Produit;
import com.example.ivan.app.services.CategorieService;
import com.example.ivan.app.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/all")
    public String showAllProducts(Model model){
       List<Produit> produits = produitService.getList();
       model.addAttribute("produits",produits);
       return "products/listProduct";
    }

    @GetMapping("/create")
    public String showAddForm(Model model){
        List<Categorie> categories = categorieService.getList();
        model.addAttribute("categories",categories);
        return "products/addProduct";
    }

    @PostMapping("/save")
    public String save(Produit produit){
        if(produit != null) {
            produit.setQuantiteStock(0);
            produit.setDateCreation(LocalDate.now());
            produitService.Add(produit);
            return "redirect:/products/all";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model ){
        List<Categorie> categories = categorieService.getList();
        model.addAttribute("categories",categories);
        model.addAttribute("unProduit", produitService.selectOne(id));
        return "products/editProduct";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("unProduit") Produit produit){
        produitService.Add(produit);
        return "redirect:/products/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        produitService.delete(id);
        return "redirect:/products/all";
    }

}
