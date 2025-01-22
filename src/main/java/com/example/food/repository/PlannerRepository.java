package com.example.food.repository;

import com.example.food.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Integer> {
}
