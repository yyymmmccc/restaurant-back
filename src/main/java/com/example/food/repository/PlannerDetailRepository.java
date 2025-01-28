package com.example.food.repository;

import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PlannerDetailRepository extends JpaRepository<PlannerDetail, Integer> {

    Optional<PlannerDetail> findByDayNumberAndPlannerAndPlace(int dayNumber, Planner planner, Place place);

    List<PlannerDetail> findByDayNumberAndPlanner(int dayNumber, Planner planner);
}
