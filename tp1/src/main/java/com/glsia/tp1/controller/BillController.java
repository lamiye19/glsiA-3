package com.glsia.tp1.controller;

import com.glsia.tp1.models.Bill;
import com.glsia.tp1.models.Produit;
import com.glsia.tp1.models.Sale;
import com.glsia.tp1.service.CustomerService;
import com.glsia.tp1.service.BillService;
import com.glsia.tp1.service.ProduitService;
import com.glsia.tp1.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProduitService produitService;

    List<Sale> sales = new ArrayList<>();


    @GetMapping("/all")
    public String afficherBill(Model model){
        model.addAttribute("bills", billService.showAllBill());
        return "bills/listBill";
    }

    @GetMapping("/create/{id}")
    public String afficherFormulaire(@PathVariable("id") int id, Model model, Produit produit, Sale sale){
        model.addAttribute("customer", customerService.selectedCustomer(id));
        model.addAttribute("produits", produitService.showAllProduit());
        model.addAttribute("product", produit);
        model.addAttribute("sale", sale);
        if(sale.getProductId() != 0 && !sales.contains(sale)){
            for (int i = 0; i < sales.size(); i++) {
                if(sales.get(i).getProductId() == sale.getProductId()){
                    model.addAttribute("sales", sales);
                    model.addAttribute("msg", "Ce produit est dejà ajouté.");
                    return "bills/addBill";
                }
            }
            Produit produit1 = produitService.selectedProduit(sale.getProductId());
            sale.setProduct(produit1);
            if(produit1.getQteStok() - sale.getQty() < produit1.getQteSeuil()){
                model.addAttribute("sales", sales);
                model.addAttribute("msg", "Vous ne pouvez pas vendre qu delà de la quantité seuil.");
                return "bills/addBill";
            }

            sales.add(sale);
        }

        model.addAttribute("msg", "");
        model.addAttribute("sales", sales);
        return "bills/addBill";
    }

    @PostMapping("/save")
    public String save(Bill bill){
        billService.saveBill(bill);
        for (int i = 0; i < sales.size(); i++) {
            Sale sale = new Sale();
            sale.setQty(sales.get(i).getQty());
            sale.setProductId(sales.get(i).getProductId());
            sale.setSalePrice(sales.get(i).getSalePrice());
            sale.setBillId(bill.getId());
            saleService.saveSale(sale);

            produitService.updateQuantityProductAfterSale(sale.getProductId(), sale.getQty());
        }
        sales.clear();
        return "redirect:/bills/all";
    }

    @GetMapping("/view/{id}")
    public String viewBill(@PathVariable("id") int id, Model model){

        model.addAttribute("sales", billService.selectedBill(id));
        model.addAttribute("customer", customerService.showAllCustomer());
        return "bills/editBill";
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
