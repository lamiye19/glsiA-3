package com.glsia.tp1.controller;

import com.glsia.tp1.models.Category;
import com.glsia.tp1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String showAllCategories(Model model)
    {
        model.addAttribute("categories",categoryService.showAllCategory() );
        return "categories/listCategories";
    }

    @GetMapping("/create")
    public String formCategory()
    {
        return "categories/addCategorie";
    }

    @PostMapping("/save")
    public String saveCategory(Category category)
    {
        categoryService.save(category);
        return "redirect:/categories/all";
    }

    @GetMapping("/edit/{id}")
    public String editFormCategory(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("uneCategorie", categoryService.selectOneCategory(id));
        return "categories/editCategory";
    }

    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute("category") Category category)
    {
        categoryService.save(category);
        return "redirect:/categories/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id)
    {
        categoryService.deleteCategory(id);
        return "redirect:/categories/all";
    }

}
