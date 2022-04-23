package com.glsia.tp1.controller;

import com.glsia.tp1.models.Bill;
import com.glsia.tp1.models.Produit;
import com.glsia.tp1.models.Sale;
import com.glsia.tp1.models.User;
import com.glsia.tp1.service.*;
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

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

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
        long cost = 0;

        if(sale.getProductId() != 0 && !sales.contains(sale)){
            Produit produit1 = produitService.selectedProduit(sale.getProductId());
            sale.setProduct(produit1);
            for (int i = 0; i < sales.size(); i++) {
                if(sales.get(i).getProductId() == sale.getProductId()){
                    model.addAttribute("sales", sales);
                    model.addAttribute("msg", "Ce produit est dejà ajouté.");
                    return "bills/addBill";
                }
            }
            if(produit1.getQteStok() - sale.getQty() < produit1.getQteSeuil()){
                model.addAttribute("sales", sales);
                model.addAttribute("msg", "Vous ne pouvez pas vendre au delà de la quantité seuil.");
                return "bills/addBill";
            }

            sales.add(sale);
            for (int i = 0; i < sales.size(); i++) {
                cost += sales.get(i).getSalePrice() * sales.get(i).getQty();
            }
        }

        model.addAttribute("msg", "");
        model.addAttribute("sales", sales);
        model.addAttribute("cost", cost);
        return "bills/addBill";
    }

    @PostMapping("/remove/{id}")
    public String removeSale(@PathVariable("id") int id, int index){
        sales.remove(index);
        return "redirect:/bills/create/"+id;
    }

    @PostMapping("/save")
    public String save(Bill bill){
        User user = userPrincipalDetailService.currentUser;

        bill.setUserId(user.getId());
        billService.saveBill(bill);
        for (Sale value : sales) {
            Sale sale = new Sale();
            sale.setQty(value.getQty());
            sale.setProductId(value.getProductId());
            sale.setSalePrice(value.getSalePrice());
            sale.setBillId(bill.getId());
            saleService.saveSale(sale);

            produitService.updateQuantityProductAfterSale(sale.getProductId(), sale.getQty());
        }
        sales.clear();
        return "redirect:/bills/all";
    }

    @GetMapping("/view/{id}")
    public String viewBill(@PathVariable("id") int id, Model model){
        Bill bill = billService.selectedBill(id);
        List<Sale> sales = saleService.selectedBillSales(id);

        long cost = 0;
        for (int i = 0; i < sales.size(); i++) {
            cost += sales.get(i).getSalePrice() * sales.get(i).getQty();
        }

        model.addAttribute("sales", sales);
        model.addAttribute("cost", cost);
        model.addAttribute("customer", bill.getCustomer());
        return "/bills/viewBill";
    }

    /*@GetMapping("/edit/{id}")
    public String formEditBill(@PathVariable("id") int id, Model model){
        model.addAttribute("bill", billService.selectedBill(id));
        model.addAttribute("customer", customerService.showAllCustomer());
        return "viewBill";
    }

    @PostMapping("/edit")
    public String editBill(Bill bill){
        billService.saveBill(bill);
        return "redirect:/bills";
    }*/

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable("id") int id, Model model){
        billService.deleteBill(id);
        return "redirect:/bills";
    }

}
