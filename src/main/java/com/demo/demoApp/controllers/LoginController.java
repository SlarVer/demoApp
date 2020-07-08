package com.demo.demoApp.controllers;

import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //TODO: ДОБАВИТЬ ОБРАБОТКУ НЕВЕРНЫХ ДАННЫХ ПОЛЬЗОВАТЕЛЯ
    @PostMapping("/login")
    public String loginProcess(@RequestParam String username, @RequestParam String password, Model model){
        if (userService.loadUser(username, password).equals("Correct")){
            return "redirect:/";
        } else return "login";
    }
}
