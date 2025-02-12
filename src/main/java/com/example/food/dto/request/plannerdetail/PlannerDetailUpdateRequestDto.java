package com.example.food.dto.request.plannerdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlannerDetailUpdateRequestDto {

    //private int plannerDetailId;
    private int dayNumber;
    private List<Integer> placeIdList;

}
