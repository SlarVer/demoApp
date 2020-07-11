package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.Query;
import com.demo.demoApp.entities.User;
import com.demo.demoApp.service.QueryService;
import com.demo.demoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/admin")
    public String allUsers(@RequestParam(defaultValue = "") String username, Model model){
        User user = (User)userService.loadUserByUsername(username);
        if (user == null) {
            model.addAttribute("logsMessage", "User not found");
        } else {
            List<Query> userLogs = queryService.userLogs(user);
            if (userLogs.isEmpty()) {
                model.addAttribute("logsMessage", user.getUsername() + "'s logs are empty");
            } else {
                model.addAttribute("userLogs", userLogs);
            }
        }
        return "admin";
    }
}
