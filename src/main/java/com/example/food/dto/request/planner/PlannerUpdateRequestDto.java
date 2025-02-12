package com.example.food.dto.request.planner;

import com.example.food.dto.request.plannerdetail.PlannerDetailUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class PlannerUpdateRequestDto {

    private int plannerId;
    private String plannerTitle;
    private Date startDate;
    private Date endDate;
    private List<PlannerDetailUpdateRequestDto> days;


}
