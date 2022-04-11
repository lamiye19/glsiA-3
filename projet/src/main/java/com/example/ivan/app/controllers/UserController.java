package com.example.ivan.app.controllers;

import com.example.ivan.app.models.User;
import com.example.ivan.app.services.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("Users")
public class UserController {

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserPrincipalDetailService userPrincipalDetailService, PasswordEncoder passwordEncoder) {
        this.userPrincipalDetailService = userPrincipalDetailService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/all")
    public String showAllUsers(Model model){
        model.addAttribute("users", userPrincipalDetailService.getList());
        return "users/listUser";
    }

    @GetMapping("/create")
    public String create(){
        return "users/addUser";
    }

    @PostMapping("/save")
    public String save(User user){
        user.setRole("VENDEUR");
        user.setPermission("");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userPrincipalDetailService.save(user);
        return "redirect:/Users/all";
    }

    @PostMapping("/updatepassword")
    public String updatepassword(@RequestParam("username") String username, @RequestParam("newpassword") String newpassword){
        User user = userPrincipalDetailService.getUserByUsername(username);
        if(user != null){
            user.setPassword(passwordEncoder.encode(newpassword));
            userPrincipalDetailService.save(user);
            return "redirect:/forgetpassword?success";
        }
        return "redirect:/forgetpassword?error";
    }


}
