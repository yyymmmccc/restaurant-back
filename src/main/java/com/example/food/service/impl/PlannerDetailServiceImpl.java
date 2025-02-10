package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import com.example.food.dto.request.plannerdetail.PlannerDetailDto;
import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.plannerdetail.PlannerDetailListResponseDto;
import com.example.food.dto.response.plannerdetail.PlannerDetailResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.repository.PlaceRepository;
import com.example.food.repository.PlannerDetailRepository;
import com.example.food.repository.PlannerRepository;
import com.example.food.service.PlannerDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PlannerDetailServiceImpl implements PlannerDetailService {

    private final PlannerRepository plannerRepository;
    private final PlannerDetailRepository plannerDetailRepository;
    private final PlaceRepository placeRepository;

    @Override
    public ResponseEntity createPlannerDetail(PlannerDetailRequestDto dto) {

        Planner planner = plannerRepository.findById(dto.getPlannerId()).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        List<Integer> allPlaceIds = new ArrayList<>();
        for (PlannerDetailDto plannerDetailDto : dto.getDays()) {
            allPlaceIds.addAll(plannerDetailDto.getPlaceIdList());
        }

        List<Place> existPlaces = placeRepository.findAllById(allPlaceIds);

        Map<Integer, Place> placeMap = new HashMap<>();
        for (Place place : existPlaces) {
            placeMap.put(place.getPlaceId(), place);
        }

        List<PlannerDetail> plannerDetails = new ArrayList<>();

        for (PlannerDetailDto plannerDetailDto : dto.getDays()) {
            for (Integer placeId : plannerDetailDto.getPlaceIdList()) {
                Place place = placeMap.get(placeId);
                if (place == null){
                    throw new CustomException(ResponseCode.BAD_REQUEST);
                }
                plannerDetails.add(PlannerDetailDto.toEntity(planner, place, plannerDetailDto.getDayNumber()));
            }
        }

        plannerDetailRepository.batchInsertPlannerDetail(plannerDetails);

        return ResponseDto.success(planner.getPlannerId());
    }

    @Override
    public ResponseEntity getPlannerDetail(String userId, int plannerId) {

        Planner planner = plannerRepository.findById(plannerId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        if(!planner.getUser().getUserId().equals(userId))
            throw new CustomException(ResponseCode.BAD_REQUEST);

        List<PlannerDetail> plannerDetailList = plannerDetailRepository.findByPlanner(planner);

        List<PlannerDetailListResponseDto> plannerDetailResponseDtoList = new ArrayList<>();

        for(PlannerDetail plannerDetail : plannerDetailList){
            plannerDetailResponseDtoList.add(PlannerDetailListResponseDto.of(plannerDetail));
        }

        Date startDate = planner.getStartDate();
        Date endDate = planner.getEndDate();

        // 출발날짜와 도착날짜 며칠차이 계산하기 0은 당일치기 / 1을 1박2일
        int calcDate = (int) ((endDate.getTime()-startDate.getTime()) / (24*60*60*1000));

        return ResponseDto.success(new PlannerDetailResponseDto(planner.getPlannerId(), startDate, endDate, calcDate, plannerDetailResponseDtoList));
    }

    @Override
    public ResponseEntity deletePlannerDetail(String userId, int plannerDetailId) {
        PlannerDetail plannerDetail = plannerDetailRepository.findById(plannerDetailId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER_DETAIL));

        String plannerUserId = plannerDetail.getPlanner().getUser().getUserId();
        if(!userId.equals(plannerUserId))
            throw new CustomException(ResponseCode.BAD_REQUEST);

        // 해당 플레너 아이디를 삭제
        plannerDetailRepository.delete(plannerDetail);

        return ResponseDto.success(null);
    }
}
