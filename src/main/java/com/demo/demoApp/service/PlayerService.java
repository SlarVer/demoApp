package com.demo.demoApp.service;

import com.demo.demoApp.entities.Player;
import com.demo.demoApp.entities.User;
import com.demo.demoApp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public List<Player> allPlayers(){
        return playerRepository.findAll();
    }

}
