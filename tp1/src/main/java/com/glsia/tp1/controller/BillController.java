package com.glsia.tp1.controller;

import com.glsia.tp1.models.Bill;
import com.glsia.tp1.models.Produit;
import com.glsia.tp1.models.Sale;
import com.glsia.tp1.service.CustomerService;
import com.glsia.tp1.service.BillService;
import com.glsia.tp1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("/all")
    public String afficherBill(Model model){
        model.addAttribute("bills", billService.showAllBill());
        return "bills/listBill";
    }

    @GetMapping("/create/{id}")
    public String afficherFormulaire(@PathVariable("id") int id, Model model, Produit produit){
        model.addAttribute("customer", customerService.selectedCustomer(id));
        model.addAttribute("produits", produitService.showAllProduit());
        model.addAttribute("product", produit);
        return "bills/addBill";
    }

    @PostMapping("/create")
    public String editProduit(@RequestParam("produit") Integer produit, @RequestParam("customer") Integer id, Model model){
        model.addAttribute("product", produitService.selectedProduit(produit));
        model.addAttribute("customer", customerService.selectedCustomer(id));
        model.addAttribute("produits", produitService.showAllProduit());
        return "bills/addBill";
//        return "redirect:/bills/create/1";
    }

    @PostMapping("/save")
    public String save(Bill bill){
        billService.saveBill(bill);
        return "redirect:/bills/all";
    }

    @GetMapping("/edit/{id}")
    public String formEditBill(@PathVariable("id") int id, Model model){
        model.addAttribute("bill", billService.selectedBill(id));
        model.addAttribute("customer", customerService.showAllCustomer());
        return "bills/editBill";
    }

    @PostMapping("/edit")
    public String editBill(Bill bill){
        billService.saveBill(bill);
        return "redirect:/bills";
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable("id") int id, Model model){
        billService.deleteBill(id);
        return "redirect:/bills";
    }

}
