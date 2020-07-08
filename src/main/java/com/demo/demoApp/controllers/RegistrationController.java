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

    //TODO: ДОБАВИТЬ ОБРАБОТКУ РЕГИСТРАЦИИ УЖЕ СУЩЕСТВУЮЩЕГО ПОЛЬЗОВАТЕЛЯ
    @PostMapping("/registration")
    public String registrationProcess(@RequestParam String username, @RequestParam String password, Model model){
        User user = new User(username, password);
        if(userService.saveUser(user)){
            return "redirect:/";
        } else return "registration";
    }
}
