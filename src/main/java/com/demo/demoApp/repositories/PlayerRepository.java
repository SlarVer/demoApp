package com.demo.demoApp.repositories;

import com.demo.demoApp.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByBirthDateBetweenAndWeightBetweenAndHeightBetween(Date dateStart, Date dateEnd, int weightStart, int weightEnd,
                                                                   int heightStart, int heightEnd);
}
