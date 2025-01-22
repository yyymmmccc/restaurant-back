package com.example.food.service;

import com.example.food.dto.request.planner.PlannerRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface PlannerService {
    ResponseEntity createPlanner(String userId, PlannerRequestDto dto);

    ResponseEntity getPlanner(String userId, int plannerId);
}
