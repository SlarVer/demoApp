package com.demo.demoApp.controllers;

import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/admin")
    public String allUsers(Model model){
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

//    @GetMapping("/admin")
//    public String allUsers() {
//        //model.addAttribute("allUsers", userService.allUsers());
//        return "admin";
//    }
}
