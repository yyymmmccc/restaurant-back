package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import com.example.food.dto.request.planner.PlannerUpdateRequestDto;
import com.example.food.dto.request.plannerdetail.PlannerDetailDto;
import com.example.food.dto.request.plannerdetail.PlannerDetailRequestDto;
import com.example.food.dto.request.plannerdetail.PlannerDetailUpdateRequestDto;
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

        // 이미 생성이 된 플래너(디테일)이 있는지 판단 후 exception
        if(!plannerDetailRepository.findByPlanner(planner).isEmpty())
            throw new CustomException(ResponseCode.BAD_REQUEST);

        List<Integer> allPlaceIds = new ArrayList<>();
        // 1일차, 2일차.. 를 순회하며 해당 날짜에 장소들을 리스트에 저장
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
                if (place == null){ // 디비에 없는 장소일 경우 오류
                    throw new CustomException(ResponseCode.BAD_REQUEST);
                }
                plannerDetails.add(PlannerDetailDto.toEntity(planner, place, plannerDetailDto.getDayNumber()));
            }
        }

        plannerDetailRepository.batchInsertPlannerDetail(plannerDetails);
        // 배치 insert가 아닌 save를 하게되면 plannerDetail에 id가 ai이므로 매 insert마다 계속 쿼리가 발생함

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

        return ResponseDto.success(new PlannerDetailResponseDto(planner.getPlannerId(), planner.getPlannerTitle(), startDate, endDate, calcDate, plannerDetailResponseDtoList));
    }

    @Override
    public ResponseEntity updatePlanner(String userId, PlannerUpdateRequestDto dto) {

        int plannerId = dto.getPlannerId();

        Planner planner = plannerRepository.findById(plannerId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        plannerDetailRepository.deleteByPlanner(planner);

        List<PlannerDetailUpdateRequestDto> dayList = dto.getDays();

        List<Integer> placeIdList = new ArrayList<>();

        // 첫날 -> 모든 장소Id 다 저장, 둘째날 -> 모든 장소 Id 다 저장
        // [[1, 2, 5], [25, 99, 192]] 이런식으로
        for(PlannerDetailUpdateRequestDto day : dayList){
            placeIdList.addAll(day.getPlaceIdList());
        }

        List<Place> placeList = placeRepository.findAllById(placeIdList);
        Map<Integer, Place> placeMap = new HashMap<>();

        // 1: place
        // 5: place....
        for(Place place : placeList){
            placeMap.put(place.getPlaceId(), place);
        }

        List<PlannerDetail> plannerDetailList = new ArrayList<>();

        for(PlannerDetailUpdateRequestDto day : dayList){
            for(Integer i : day.getPlaceIdList()){
                // 반복문을 돌면서 placeMap에 해당 place가 있는지 검사
                Place place = placeMap.get(i);
                if(place == null) {
                    throw new CustomException(ResponseCode.NOT_FOUND_PLACE);
                }

                plannerDetailList.add(
                        PlannerDetail.builder()
                                .dayNumber(day.getDayNumber())
                                .place(place)
                                .planner(planner)
                                .build());
            }
        }

        plannerDetailRepository.batchInsertPlannerDetail(plannerDetailList);

        return ResponseDto.success(null);

        // plannerDetailId 리스트를 for문으로 받아 온 후 해당 데이터 다 삭제 후
        // -> 다시 저장

        /*
        days:[{
            dayNumber: 0
            placeIdList: [1, 3, 5]
        }],
        [{
            dayNumber: 1
            placeIdList: [2, 3, 5]
        }]


         */




    }

    /*
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

     */
}
