package com.demo.demoApp.controllers;

import com.demo.demoApp.entities.Player;
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
import java.util.List;

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

    @PostMapping("/query")
    public String queryExecute(@RequestParam Date dateStart, @RequestParam Date dateEnd, @RequestParam int weightStart,
                               @RequestParam int weightEnd, @RequestParam int heightStart, @RequestParam int heightEnd,
                               Model model, Principal principal) {
        List<Player> selectedPlayers = queryService.select(dateStart, dateEnd, weightStart, weightEnd, heightStart, heightEnd, principal);
        if (selectedPlayers.isEmpty()) {
            model.addAttribute("message", "No matching players");
        } else {
            model.addAttribute("selectedPlayers", selectedPlayers);
        }
        model.addAttribute("allPlayers", playerService.allPlayers());
        return "query";
    }
}
