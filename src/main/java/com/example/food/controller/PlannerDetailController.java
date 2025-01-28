package com.example.food.controller;

import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import com.example.food.service.PlannerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner-detail")
@RequiredArgsConstructor
public class PlannerDetailController {

    private final PlannerDetailService plannerDetailService;

    @PostMapping("")
    public ResponseEntity createPlannerDetail(@RequestBody PlannerDetailRequestDto dto){

        return plannerDetailService.createPlannerDetail(dto);
    }

    @GetMapping("")
    public ResponseEntity getPlannerDetail(@AuthenticationPrincipal String userId,
                                           @RequestParam(name = "plannerId") int plannerId,
                                           @RequestParam(name = "dayNumber") int dayNumber){

        return plannerDetailService.getPlannerDetail(userId, plannerId, dayNumber);
    }

    @DeleteMapping("")
    public ResponseEntity deletePlannerDetail(@AuthenticationPrincipal String userId,
                                              @RequestParam(name = "plannerDetailId") int plannerDetailId){

        return plannerDetailService.deletePlannerDetail(userId, plannerDetailId);
    }


}
