package com.example.food.dto.request.plannerdetail;

import com.example.food.dto.request.place.PlaceRequestDto;
import com.example.food.dto.request.planner.PlannerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDetailRequestDto {

    private int plannerId;
    private List<PlannerDetailDto> days;

}
