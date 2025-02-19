package com.example.food.dto.request.place;

import com.example.food.domain.Place;
import com.example.food.domain.Planner;
import com.example.food.domain.PlannerDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequestDto {

    private int placeId;
    private int dayNumber;

    public Place toPlaceEntity(){
        return Place.builder()
                .placeId(this.placeId)
                .build();
    }

    public PlannerDetail toPlannerDetailEntity(Planner planner, Place place){
        return PlannerDetail.builder()
                .dayNumber(this.dayNumber)
                .planner(planner)
                .place(place)
                .build();
    }
}
