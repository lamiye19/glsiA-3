package com.glsia.tp1.controller;

import com.glsia.tp1.models.Customer;
import com.glsia.tp1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public String afficherCustomer(Model model)
    {
        model.addAttribute("customers", customerService.showAllCustomer());
        return "customers/listCustomer";
    }

    @GetMapping("/create")
    public String formCreateCustomer(Model model)
    {
        return "customers/addCustomer";
    }

    @PostMapping("/save")
    public String save(Customer customer)
    {
        customerService.saveCustomer(customer);
        return "redirect:/customers/all";
    }

    @GetMapping("/view/{id}")
    public String customerProfile(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("customer", customerService.selectedCustomer(id));
        return "customers/viewCustomer";
    }

    @GetMapping("/edit/{id}")
    public String formEditCustomer(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("customer", customerService.selectedCustomer(id));
        return "customers/editCustomer";
    }

    @PostMapping("/edit")
    public String editCustomer(Customer customer ){
        customer.setUpdatedAt(LocalDate.now());
        customerService.saveCustomer(customer);
        return "redirect:/customers/all";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id)
    {
        customerService.deleteCustomer(id);
        return "redirect:/customers/all";
    }

}
