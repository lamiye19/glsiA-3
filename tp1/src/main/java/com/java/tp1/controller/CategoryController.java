package com.java.tp1.controller;

import com.java.tp1.model.Category;
import com.java.tp1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String read(Model model){
        model.addAttribute("listCategory", categoryService.showAllCategory());
        return "categories/showCategory";
    }

    @GetMapping("/add")
    public String add(){
        return "categories/addCategory";
    }

    @PostMapping("/create")
    public String create(Category category){
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("aCategory", categoryService.selectedCategory(id));
        return "categories/editCategory";
    }

    @PostMapping("/update")
    public String update(Category category){
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
