package com.example.food.dto.request.plannerdetail;

import com.example.food.dto.request.place.PlaceRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDetailRequestDto {

    private int plannerId;
    private PlaceRequestDto[] placeRequestList;

}
