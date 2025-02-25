package com.example.food.controller;

import com.example.food.common.ResponseCode;
import com.example.food.dto.request.planner.PlannerCreateRequestDto;
import com.example.food.dto.request.planner.PlannerUpdateRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.service.PlannerDetailService;
import com.example.food.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner")
@RequiredArgsConstructor
@Slf4j
public class PlannerController {

    private final PlannerService plannerService;
    private final PlannerDetailService plannerDetailService;

    @PostMapping("")
    public ResponseEntity createPlanner(@AuthenticationPrincipal String userId,
                                        @RequestBody PlannerCreateRequestDto dto){

      return plannerService.createPlanner(userId, dto);
    }

    @GetMapping("")
    public ResponseEntity getPlanner(@AuthenticationPrincipal String userId,
                                     @RequestParam(name = "plannerId") int plannerId){

        return plannerService.getPlanner(userId, plannerId);
    }

    @GetMapping("/list")
    public ResponseEntity getPlannerList(@AuthenticationPrincipal String userId){

        return plannerService.getPlannerList(userId);
    }

    @PatchMapping("")
    public ResponseEntity updatePlanner(@AuthenticationPrincipal String userId,
                                        @RequestBody PlannerUpdateRequestDto dto){

        // 플래너 변경시 -> 제목, 날짜는 planner 이며, 장소 등은 plannerDetail 이므로 하나의 요청에 둘을 변경

        ResponseEntity plannerResponse = plannerService.updatePlanner(userId, dto);
        if(!plannerResponse.getStatusCode().equals(HttpStatus.OK)) throw new CustomException(ResponseCode.BAD_REQUEST);

        ResponseEntity plannerDetailResponse = plannerDetailService.updatePlanner(userId, dto);
        if(!plannerDetailResponse.getStatusCode().equals(HttpStatus.OK)) throw new CustomException(ResponseCode.BAD_REQUEST);


        return ResponseDto.success(dto.getPlannerId());

    }

    @DeleteMapping("/{plannerId}")
    public ResponseEntity deletePlanner(@AuthenticationPrincipal String userId,
                                        @PathVariable int plannerId){

        return plannerService.deletePlanner(userId, plannerId);
    }


}
