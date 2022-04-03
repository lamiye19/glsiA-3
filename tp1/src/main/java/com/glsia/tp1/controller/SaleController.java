package com.glsia.tp1.controller;

import com.glsia.tp1.models.Sale;
import com.glsia.tp1.service.ProduitService;
import com.glsia.tp1.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private ProduitService productService;

    @GetMapping("/all")
    public String afficherSale(Model model){
        model.addAttribute("sales", saleService.showAllSale());
        return "sales/listSale";
    }

    @GetMapping("/create/{id}")
    public String afficherFormulaire(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.selectedProduit(id));
        return "sales/addSale";
    }

    @PostMapping("/save")
    public String save(Sale sale){
        saleService.saveSale(sale);
        productService.updateQuantityProductAfterSale(sale.getProductId(), sale.getQty());
        return "redirect:/sales/all";
    }

    @GetMapping("/edit/{id}")
    public String formEditSale(@PathVariable("id") int id, Model model){
        model.addAttribute("sale", saleService.selectedSale(id));
        model.addAttribute("produit", productService.showAllProduit());
        return "sales/editSale";
    }

    @PostMapping("/edit")
    public String editSale(Sale sale){
        saleService.saveSale(sale);
        return "redirect:/sales";
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable("id") int id, Model model){
        saleService.deleteSale(id);
        return "redirect:/sales";
    }
}
