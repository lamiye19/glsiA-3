package com.glsia.tp1.controller;

import com.glsia.tp1.models.Approvisionnement;
import com.glsia.tp1.service.ApprovisionnementService;
import com.glsia.tp1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/approvisionnement")
public class ApprovisionnementController {

    @Autowired
    private ApprovisionnementService approvisionnementService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("/index")
    public String showAllApprovisionnement(Model model)
    {
        model.addAttribute("listApprov",
                approvisionnementService.showAllApprovisionnement());
        return "approvisionnement/index";
    }

    @PostMapping("/create")
    public String saveApprovisionnement(Approvisionnement approvisionnement)
    {
        approvisionnement.setDateApprov(LocalDate.now());
        approvisionnementService.save(approvisionnement);
        produitService.updateQuantityProduct(approvisionnement.getProduit_id(),
                approvisionnement.getQuantite());
        return "redirect:/produit/show";
    }
}
