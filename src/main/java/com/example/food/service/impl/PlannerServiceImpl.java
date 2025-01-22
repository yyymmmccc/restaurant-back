package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.Planner;
import com.example.food.domain.User;
import com.example.food.dto.request.planner.PlannerRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.planner.CreatePlannerResponseDto;
import com.example.food.dto.response.planner.GetPlannerResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.repository.PlannerRepository;
import com.example.food.repository.UserRepository;
import com.example.food.service.PlannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannerServiceImpl implements PlannerService {

    private final UserRepository userRepository;
    private final PlannerRepository plannerRepository;

    @Override
    @Transactional
    public ResponseEntity createPlanner(String userId, PlannerRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_USER));

        Planner planner = dto.toEntity(user);
        plannerRepository.save(planner);

        return ResponseDto.success(CreatePlannerResponseDto.of(planner));
    }

    @Override
    public ResponseEntity getPlanner(String userId, int plannerId) {
        log.info("플래너아이디 : " + plannerId);
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        Date startDate = planner.getStartDate();
        Date endDate = planner.getEndDate();

        // 출발날짜와 도착날짜 며칠차이 계산하기 0은 당일치기 / 1을 1박2일
        int calcDate = (int) ((endDate.getTime()-startDate.getTime()) / (24*60*60*1000));

        return ResponseDto.success(GetPlannerResponseDto.of(planner, calcDate));
    }
}
