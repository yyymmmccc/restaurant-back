package com.example.food.controller;

import com.example.food.dto.request.planner.PlannerRequestDto;
import com.example.food.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner")
@RequiredArgsConstructor
@Slf4j
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("")
    public ResponseEntity createPlanner(@AuthenticationPrincipal String userId,
                                        @RequestBody PlannerRequestDto dto){

      return plannerService.createPlanner(userId, dto);
    }

    @GetMapping("")
    public ResponseEntity getPlanner(@AuthenticationPrincipal String userId,
                                     @RequestParam(name = "plannerId") int plannerId){

        log.info("플래너아이디12 : " + plannerId);

        return plannerService.getPlanner(userId, plannerId);
    }


}
