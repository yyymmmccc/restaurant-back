package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.Planner;
import com.example.food.domain.User;
import com.example.food.dto.request.planner.PlannerCreateRequestDto;
import com.example.food.dto.request.planner.PlannerUpdateRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.planner.CreatePlannerResponseDto;
import com.example.food.dto.response.planner.GetPlannerResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.repository.PlannerDetailRepository;
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
@Transactional
public class PlannerServiceImpl implements PlannerService {

    private final UserRepository userRepository;
    private final PlannerRepository plannerRepository;
    private final PlannerDetailRepository plannerDetailRepository;

    @Override
    public ResponseEntity createPlanner(String userId, PlannerCreateRequestDto dto) {

        User user = findByUser(userId);

        Planner planner = dto.toEntity(user);
        plannerRepository.save(planner);

        return ResponseDto.success(CreatePlannerResponseDto.of(planner));
    }

    @Override
    public ResponseEntity getPlanner(String userId, int plannerId) {

        Planner planner = findByPlanner(plannerId);

        Date startDate = planner.getStartDate();
        Date endDate = planner.getEndDate();

        // 출발날짜와 도착날짜 며칠차이 계산하기 0은 당일치기 / 1을 1박2일
        int calcDate = (int) ((endDate.getTime()-startDate.getTime()) / (24*60*60*1000));

        return ResponseDto.success(GetPlannerResponseDto.of(planner, calcDate));
    }

    @Override
    public ResponseEntity deletePlanner(String userId, int plannerId) {

        Planner planner = findByPlanner(plannerId);
        User user = findByUser(userId);

        if(!planner.getUser().equals(user))
            throw new CustomException(ResponseCode.BAD_REQUEST);

        plannerDetailRepository.deletePlannerDetail(planner);
        plannerRepository.deleteById(planner.getPlannerId());

        return ResponseDto.success(null);
    }

    @Override
    public ResponseEntity updatePlanner(String userId, PlannerUpdateRequestDto dto) {

        int plannerId = dto.getPlannerId();
        Planner planner = findByPlanner(plannerId);

        User user = findByUser(userId);
        // 유저가 해당 유저인지 검증 -> 만약 다르다면 exception 발생으로 더 이상 코드 진행 x
        verifyUser(user, planner);

        String title = dto.getPlannerTitle();
        //Date startDate = dto.getStartDate();
        //Date endDate = dto.getEndDate();

        if(!planner.getPlannerTitle().equals(title)) {
            log.info("requestTitle : " + title);
            planner.updateTitle(title);
            log.info("updateTitle : " + planner.getPlannerTitle());
        }

        //if(!planner.getStartDate().equals(startDate)) planner.updateStartDate(startDate);

        //if(!planner.getStartDate().equals(endDate)) planner.updateEndDate(endDate);

        return ResponseDto.success(null);
    }

    public User findByUser(String userId){

        User user = userRepository.findById(userId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_USER));

        return user;
    }

    public Planner findByPlanner(int plannerId){

        Planner planner = plannerRepository.findById(plannerId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_PLANNER));

        return planner;
    }

    public void verifyUser(User user, Planner planner){
        if(!user.equals(planner.getUser()))
            throw new CustomException(ResponseCode.BAD_REQUEST);

    }
}
