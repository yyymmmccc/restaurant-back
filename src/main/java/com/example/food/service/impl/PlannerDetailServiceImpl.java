package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import com.example.food.dto.request.place.PlaceRequestDto;
import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.place.PlaceResponseDto;
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
public class PlannerDetailServiceImpl implements PlannerDetailService {

    private final PlannerRepository plannerRepository;
    private final PlannerDetailRepository plannerDetailRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    @Override
    public ResponseEntity createPlannerDetail(PlannerDetailRequestDto dto) {

        Planner planner = plannerRepository.findById(dto.getPlannerId()).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        List<Place> placeList = new ArrayList<>();
        List<PlannerDetail> plannerDetailList = new ArrayList<>();

        for(PlaceRequestDto placeDto : dto.getPlaceRequestList()){

            Place place = placeDto.toPlaceEntity();

            if(placeRepository.existsById(place.getPlaceId())){ // 장소가 이미 등록되어있음

                PlannerDetail isPlannerDetailExists = plannerDetailRepository.findByDayNumberAndPlannerAndPlace
                        (placeDto.getDayNumber(), planner, place).orElse(null);

                if(isPlannerDetailExists != null)
                    throw new CustomException(ResponseCode.DUP_PLACE);

                plannerDetailList.add(placeDto.toPlannerDetailEntity(planner, place));
            }
            else{
                placeList.add(place); // 저장이 안된 경우
                plannerDetailList.add(placeDto.toPlannerDetailEntity(planner, place));
            }
        }

        placeRepository.saveAll(placeList);
        plannerDetailRepository.saveAll(plannerDetailList);

        return ResponseDto.success(planner.getPlannerId());
    }

    @Override
    public ResponseEntity getPlannerDetail(String userId, int plannerId, int dayNumber) {

        Planner planner = plannerRepository.findById(plannerId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        if(!planner.getUser().getUserId().equals(userId))
            throw new CustomException(ResponseCode.BAD_REQUEST);

        List<PlannerDetail> plannerDetailList = plannerDetailRepository.findByDayNumberAndPlanner(dayNumber, planner);

        List<PlaceResponseDto> placeResponseDtoList = new ArrayList<>();

        for(PlannerDetail plannerDetail : plannerDetailList){

            placeResponseDtoList.add(PlaceResponseDto.of(plannerDetail.getPlace(), plannerDetail.getPlannerDetailId()));
        }

        return ResponseDto.success(placeResponseDtoList);
    }

    @Transactional
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
