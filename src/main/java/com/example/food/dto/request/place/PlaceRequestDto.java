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
    private String categoryCode;
    private String placeName;
    private String phone;
    private String addressName;
    private double x;
    private double y;

    public Place toPlaceEntity(){
        return Place.builder()
                .placeId(this.placeId)
                .categoryCode(this.categoryCode)
                .placeName(this.placeName)
                .phone(this.phone)
                .addressName(this.addressName)
                .x(this.x)
                .y(this.y)
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
