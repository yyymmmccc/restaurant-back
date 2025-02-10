package com.example.food.controller;

import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import com.example.food.service.PlannerDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner-detail")
@RequiredArgsConstructor
@Slf4j
public class PlannerDetailController {

    private final PlannerDetailService plannerDetailService;

    @PostMapping("")
    public ResponseEntity createPlannerDetail(@RequestBody PlannerDetailRequestDto dto){

        return plannerDetailService.createPlannerDetail(dto);
    }

    @GetMapping("")
    public ResponseEntity getPlannerDetail(@AuthenticationPrincipal String userId,
                                           @RequestParam(name = "plannerId") int plannerId){

        return plannerDetailService.getPlannerDetail(userId, plannerId);
    }

    @DeleteMapping("")
    public ResponseEntity deletePlannerDetail(@AuthenticationPrincipal String userId,
                                              @RequestParam(name = "plannerDetailId") int plannerDetailId){

        return plannerDetailService.deletePlannerDetail(userId, plannerDetailId);
    }


}
