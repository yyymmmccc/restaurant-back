package com.example.food.dto.response.search;

import com.example.food.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResponseDto {

    private int placeId;
    private int categoryCode;
    private int areaCode;
    private String placeName;
    private String phone;
    private String addressName;
    private double x;
    private double y;
    private String image;

    public static SearchResponseDto of(Place place){
        return SearchResponseDto.builder()
                .placeId(place.getPlaceId())
                .categoryCode(place.getCategoryCode())
                .areaCode(place.getAreaCode())
                .placeName(place.getPlaceName())
                .phone(place.getPhone())
                .addressName(place.getAddressName())
                .x(place.getX())
                .y(place.getY())
                .image(place.getImage())
                .build();
    }
}
