package com.java.tp1.controller;

import com.java.tp1.model.Product;
import com.java.tp1.repository.ProductRepository;
import com.java.tp1.service.CategoryService;
import com.java.tp1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String read(Model model){
        model.addAttribute("listProduct", productService.showAllProduct());
        return "products/showProduct";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("listCategory", categoryService.showAllCategory());
        return "products/addProduct";
    }

    @PostMapping("/create")
    public String create(Product product){
        product.setQteStock(0);
        product.setDateCreation(LocalDate.now());
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("aProduct", productService.selectedProduct(id));
        model.addAttribute("listCategory", categoryService.showAllCategory());
        return "products/editProduct";
    }

    @PostMapping("/update")
    public String update(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
