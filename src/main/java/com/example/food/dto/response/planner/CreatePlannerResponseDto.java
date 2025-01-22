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
public class CreatePlannerResponseDto {

    private int plannerId;
    private String plannerTitle;
    private Date startDate;
    private Date endDate;

    public static CreatePlannerResponseDto of(Planner planner){
        return CreatePlannerResponseDto.builder()
                .plannerId(planner.getPlannerId())
                .plannerTitle(planner.getPlannerTitle())
                .startDate(planner.getStartDate())
                .endDate(planner.getEndDate())
                .build();
    }
}
