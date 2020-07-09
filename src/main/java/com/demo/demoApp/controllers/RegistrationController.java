package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.User;
import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    //TODO: Immediate login after registration
    @PostMapping("/registration")
    public String registrationProcess(@RequestParam String username, @RequestParam String password,
                                      @RequestParam String passwordConfirm, Model model){
        if (userService.loadUserByUsername(username) != null){
            model.addAttribute("message", "Username is already taken!");
            return "registration";
        } else if (!password.equals(passwordConfirm)){
            model.addAttribute("message", "Passwords don't match!");
            return "registration";
        } else {
            userService.saveUser(new User(username, password));
            return "redirect:login";
        }
    }
}
