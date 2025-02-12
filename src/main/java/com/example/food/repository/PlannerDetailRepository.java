package com.example.food.repository;

import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PlannerDetailRepository extends JpaRepository<PlannerDetail, Integer>, PlannerDetailCustom {

    List<PlannerDetail> findByPlanner(Planner planner);

    @Modifying
    @Query("DELETE FROM plannerDetail pd WHERE pd.planner = :planner")
    void deletePlannerDetail(@Param("planner") Planner planner);

    void deleteByPlanner(Planner planner);
}
