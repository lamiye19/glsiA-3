package com.glsia.tp1.controller;

import com.glsia.tp1.models.Approvisionnement;
import com.glsia.tp1.service.ApprovisionnementService;
import com.glsia.tp1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("produit", produitService.showAllProduit());
        model.addAttribute("approvisionnements",
                approvisionnementService.showAllApprovisionnement());
        return "approvisionnement/index";
    }

    @PostMapping("/save")
    public String saveApprovisionnement(Approvisionnement approvisionnement)
    {
        approvisionnement.setDateCreation(LocalDate.now());
        approvisionnement.setDateModification(LocalDate.now());
        approvisionnementService.save(approvisionnement);

        produitService.updateQuantityProduct(approvisionnement.getProduit_id(),
                approvisionnement.getQuantite());
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String editApprovisionnement(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("oneApprovisionnement", approvisionnementService.selectOneApprovisionnement(id));
        model.addAttribute("oneProduit", produitService.selectedProduit(id));

        return "approvisionnement/editApprovisionnement";
    }

    @PostMapping("/edit")
    public String updateApprovisionnement(@ModelAttribute("approvisionnement") Approvisionnement approvisionnement)
    {
        //approvisionnement.setDateCreation(LocalDate.now());
        approvisionnement.setDateModification(LocalDate.now());
        approvisionnementService.updateApprovisionnement(approvisionnement.getId(), approvisionnement.getQuantite());

        produitService.updateQuantityProduct(approvisionnement.getProduit_id(),
                approvisionnement.getQuantite());
        return "redirect:/approvisionnement/index";

    }

    @GetMapping("/delete/{id}")
    public String deleteApprovisionnement(@ModelAttribute("approvisionnement") Approvisionnement approvisionnement)
    {
        approvisionnementService.updateQuantityProductBeforeDeleteApprovisionnement(approvisionnement.getProduit_id(),
                approvisionnement.getQuantite());

        approvisionnementService.deleteApprovisionnement(approvisionnement.getId());
        /*
        produitService.updateQuantityProduct(approvisionnement.getProduit_id(),
                approvisionnement.getQuantite());*/
        return "redirect:/approvisionnement/index";
    }

}
