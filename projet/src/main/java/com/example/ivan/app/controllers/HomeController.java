package com.example.ivan.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/login")
    public String index(){
         return "login";
    }

    @GetMapping("/forgetpassword")
    public String forgetpassword(){
        return "forgetpassword";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Dashboard/index";
    }



}
