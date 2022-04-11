package com.example.ivan.app.controllers;

import com.example.ivan.app.models.Approvisionnement;
import com.example.ivan.app.models.Categorie;
import com.example.ivan.app.services.ApprovisionnementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/approvisionnements")
public class ApprovisionnementController {

    private ApprovisionnementService approvisionnementService;


    @GetMapping("/all")
    public String showAllApprovisionnemts(Model model){
        List<Approvisionnement> approvisionnements = approvisionnementService.getList();
        model.addAttribute("approvisionnements",approvisionnements);
        return "approvisionnements/listApprovisionnements";
    }

    @GetMapping("/create")
    public String showAddForm(){
        return "approvisionnements/addApprovisionnement";
    }

    @PostMapping("/save")
    public String save(Approvisionnement approvisionnement){
            approvisionnementService.Add(approvisionnement);
        return "redirect:/approvisionnements/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model ){
        model.addAttribute("unApprovisionnement", approvisionnementService.selectOne(id));
        return "approvisionnements/editApprovisionnement";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("unApprovisionnement") Approvisionnement approvisionnement){
        approvisionnementService.Add(approvisionnement);
        return "redirect:/approvisionnements/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        approvisionnementService.delete(id);
        return "redirect:/approvisionnements/all";
    }


}
