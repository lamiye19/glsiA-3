package com.example.ivan.app.controllers;

import com.example.ivan.app.models.Categorie;
import com.example.ivan.app.models.Produit;
import com.example.ivan.app.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;


    @GetMapping("/all")
    public String showAllCategories(Model model){
        List<Categorie> categories = categorieService.getList();
        model.addAttribute("categories",categories);
        return "categories/listCategories";
    }

    @GetMapping("/create")
    public String showAddForm(){
        return "categories/addCategorie";
    }

    @PostMapping("/save")
    public String save(Categorie categorie){
        categorieService.Add(categorie);
        return "redirect:/categories/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model ){
        model.addAttribute("unCategorie", categorieService.selectOne(id));
        return "categories/editCategory";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("unProduit") Categorie categorie){
        categorieService.Add(categorie);
        return "redirect:/categories/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categorieService.delete(id);
        return "redirect:/categories/all";
    }
}

