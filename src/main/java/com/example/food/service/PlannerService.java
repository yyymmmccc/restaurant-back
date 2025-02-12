package com.example.food.service;

import com.example.food.dto.request.planner.PlannerCreateRequestDto;
import com.example.food.dto.request.planner.PlannerUpdateRequestDto;
import org.springframework.http.ResponseEntity;


public interface PlannerService {
    ResponseEntity createPlanner(String userId, PlannerCreateRequestDto dto);

    ResponseEntity getPlanner(String userId, int plannerId);

    ResponseEntity deletePlanner(String userId, int plannerId);

    ResponseEntity updatePlanner(String userId, PlannerUpdateRequestDto dto);
}
