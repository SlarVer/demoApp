package com.demo.demoApp.repositories;

import com.demo.demoApp.entities.Query;
import com.demo.demoApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findAllByAuthor(User user);
}
