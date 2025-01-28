package com.example.food.service;

import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import org.springframework.http.ResponseEntity;

public interface PlannerDetailService {
    ResponseEntity createPlannerDetail(PlannerDetailRequestDto dto);

    ResponseEntity getPlannerDetail(String userId, int plannerId, int dayNumber);

    ResponseEntity deletePlannerDetail(String userId, int plannerDetailId);
}
