package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getName());
        }
        return "main";
    }
}
