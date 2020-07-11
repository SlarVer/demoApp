package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.Query;
import com.demo.demoApp.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class LogsController {
    @Autowired
    QueryService queryService;

    @GetMapping("/logs")
    public String logs(Model model, Principal principal){
        List<Query> logs = queryService.logs(principal);
        if (logs.isEmpty()) {
            model.addAttribute("message", "Your have no logs");
        } else {
            model.addAttribute("logs", logs);
        }
        return "logs";
    }
}
