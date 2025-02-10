package com.example.food.repository;

import com.example.food.domain.PlannerDetail;

import java.util.List;

public interface PlannerDetailCustom {

    void batchInsertPlannerDetail(List<PlannerDetail> plannerDetails);
}
