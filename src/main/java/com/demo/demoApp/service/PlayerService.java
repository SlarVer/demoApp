package com.demo.demoApp.service;

import com.demo.demoApp.entities.Player;
import com.demo.demoApp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    public List<Player> allPlayers(){
        return playerRepository.findAll();
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Player findByName(String name, String surname) {
        return  playerRepository.findByNameAndSurname(name, surname);
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }

    public List<Player> findByStats(Date dateStart, Date dateEnd, int weightStart, int weightEnd, int heightStart, int heightEnd) {
        return playerRepository.findAllByBirthDateBetweenAndWeightBetweenAndHeightBetween(dateStart, dateEnd, weightStart, weightEnd, heightStart, heightEnd);
    }

    public void deletePlayers(List<Player> players) {
        for (Player player: players) {
            playerRepository.delete(player);
        }
    }
}
