package com.demo.demoApp.controllers;

import com.demo.demoApp.service.PlayerService;
import com.demo.demoApp.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Date;

@Controller
public class QueryController {
    @Autowired
    QueryService queryService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/query")
    public String query(Model model) {
        model.addAttribute("allPlayers", playerService.allPlayers());
        return "query";
    }

//    @PostMapping("/query")
//    public String queryExecute(@RequestParam String dateStart, @RequestParam String dateEnd, @RequestParam String weightStart,
//                               @RequestParam String weightEnd, @RequestParam String heightStart, @RequestParam String heightEnd,
//                               Model model, Principal principal) {
//        queryService.select(Date.valueOf(dateStart), Date.valueOf(dateEnd), Integer.parseInt(weightStart),
//                Integer.parseInt(weightEnd), Integer.parseInt(heightStart), Integer.parseInt(heightEnd), principal);
//        return "query";
//    }
@PostMapping("/query")
public String queryExecute(@RequestParam Date dateStart, @RequestParam Date dateEnd, @RequestParam int weightStart,
                           @RequestParam int weightEnd, @RequestParam int heightStart, @RequestParam int heightEnd,
                           Model model, Principal principal) {
    queryService.select(dateStart, dateEnd, weightStart, weightEnd, heightStart, heightEnd, principal);
    return "query";
}
}
