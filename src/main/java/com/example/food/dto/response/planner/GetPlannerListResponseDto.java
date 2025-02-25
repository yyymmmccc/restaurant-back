package com.example.food.dto.response.planner;

import com.example.food.domain.Planner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPlannerListResponseDto {

    private int plannerId;
    private String plannerTitle;
    private Date startDate;
    private Date endDate;

    public static GetPlannerListResponseDto of(Planner planner){
        return GetPlannerListResponseDto.builder()
                .plannerId(planner.getPlannerId())
                .plannerTitle(planner.getPlannerTitle())
                .startDate(planner.getStartDate())
                .endDate(planner.getEndDate())
                .build();
    }
}
