package com.demo.demoApp.repositories;

import com.demo.demoApp.entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Long> {
}
