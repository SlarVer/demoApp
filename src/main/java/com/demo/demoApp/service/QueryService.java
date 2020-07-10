package com.demo.demoApp.service;

import com.demo.demoApp.entities.Player;
import com.demo.demoApp.entities.Query;
import com.demo.demoApp.entities.User;
import com.demo.demoApp.repositories.PlayerRepository;
import com.demo.demoApp.repositories.QueryRepository;
import com.demo.demoApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Service
public class QueryService {
    @Autowired
    QueryRepository queryRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    UserRepository userRepository;

    public List<Player> select(Date dateStart, Date dateEnd, int weightStart, int weightEnd, int heightStart, int heightEnd, Principal principal) {
        List<Player> resultList = playerRepository.findAllByBirthDateBetweenAndWeightBetweenAndHeightBetween(
                dateStart, dateEnd, weightStart, weightEnd, heightStart, heightEnd);
        queryRepository.save(new Query("Birthdate: " + dateStart + " - " + dateEnd + ", weight: " +
                weightStart + " - " + weightEnd + ", height: " + heightStart + " - " + heightEnd,
                resultList.size() + " players selected", userRepository.findByUsername(principal.getName())));
        return resultList;
    }
}
