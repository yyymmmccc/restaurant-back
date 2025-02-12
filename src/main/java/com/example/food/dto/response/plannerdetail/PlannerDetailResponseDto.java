package com.example.food.dto.response.plannerdetail;

import com.example.food.domain.Place;
import com.example.food.domain.PlannerDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDetailResponseDto {

    private int plannerId;
    private String plannerTitle;
    private Date startDate;
    private Date endDate;
    private int totalDate;
    private List<PlannerDetailListResponseDto> list;

}
