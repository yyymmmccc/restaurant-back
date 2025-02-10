package com.example.food.dto.request.plannerdetail;

import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlannerDetailDto {

    private int dayNumber;
    private List<Integer> placeIdList;

    public static PlannerDetail toEntity(Planner planner, Place place, int dayNumber){
        return PlannerDetail.builder()
                .planner(planner)
                .place(place)
                .dayNumber(dayNumber)
                .build();
    }

}
