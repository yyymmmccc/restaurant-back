package com.example.food.dto.request.plannerdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDetailRequestDto {

    private int plannerId;
    private List<PlannerDetailDto> days;

}
