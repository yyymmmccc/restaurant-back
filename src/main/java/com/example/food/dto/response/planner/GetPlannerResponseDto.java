package com.example.food.dto.response.planner;

import com.example.food.domain.Planner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPlannerResponseDto {

    private int plannerId;
    private String plannerTitle;
    private int calcDate;

    public static GetPlannerResponseDto of(Planner planner, int calcDate){
        return GetPlannerResponseDto.builder()
                .plannerId(planner.getPlannerId())
                .plannerTitle(planner.getPlannerTitle())
                .calcDate(calcDate)
                .build();
    }
}
