package com.example.food.dto.response.place;

import com.example.food.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceResponseDto {

    private int plannerDetailId;
    private int placeId;
    private String placeName;
    private String addressName;
    private double x;
    private double y;

    public static PlaceResponseDto of(Place place, int plannerDetailId){
        return PlaceResponseDto.builder()
                .plannerDetailId(plannerDetailId)
                .placeId(place.getPlaceId())
                .placeName(place.getPlaceName())
                .addressName(place.getAddressName())
                .x(place.getX())
                .y(place.getY())
                .build();
    }

}
