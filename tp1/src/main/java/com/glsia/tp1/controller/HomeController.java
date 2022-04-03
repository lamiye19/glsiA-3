package com.glsia.tp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/login"})
    public String index()
    {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "Dashboard/index";
    }
}
