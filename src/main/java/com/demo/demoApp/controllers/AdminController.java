package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.Query;
import com.demo.demoApp.entities.User;
import com.demo.demoApp.service.QueryService;
import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    QueryService queryService;

    @ModelAttribute
    public void globalPageAttributes(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allLogs", queryService.allLogs());
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @PostMapping("/admin/show")
    public String logsUser(@RequestParam(defaultValue = "") String username, Model model){
        try{
            User user = (User)userService.loadUserByUsername(username);
            List<Query> userLogs = queryService.userLogs(user);
            if (userLogs.isEmpty()) {
                model.addAttribute("logsMessage", username + "'s logs are empty");
            } else {
                model.addAttribute("userLogs", userLogs);
            }
        } catch (UsernameNotFoundException exception) {
            model.addAttribute("logsMessage", exception.getMessage());
        }
        return "admin";
    }

    @PostMapping("/admin/block")
    public String deleteUser(@RequestParam(defaultValue = "") String blockUsername, Model model) {
        try {
            userService.blockUser(blockUsername);
            model.addAttribute("blockMessage", "User successfully blocked");
        } catch (UsernameNotFoundException exception){
            model.addAttribute("blockMessage", exception.getMessage());
        }
        return "admin";
    }
}
