package com.demo.demoApp.controllers;

import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class AdminController {
    @Autowired
    UserService userService;

//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }

    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }
}
