package com.example.food.repository;

import com.example.food.domain.Planner;
import com.example.food.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Integer> {

    List<Planner> findByUserOrderByUpdateDateDesc(User user);
}
