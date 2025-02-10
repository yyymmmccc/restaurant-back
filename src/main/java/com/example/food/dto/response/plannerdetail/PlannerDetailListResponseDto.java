package com.example.food.dto.response.plannerdetail;

import com.example.food.domain.PlannerDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannerDetailListResponseDto {

    private int plannerDetailId;
    private int dayNumber;
    private int placeId;
    private String image;
    private String placeName;
    private String addressName;
    private double x;
    private double y;

    public static PlannerDetailListResponseDto of(PlannerDetail plannerDetail){
        return PlannerDetailListResponseDto.builder()
                .plannerDetailId(plannerDetail.getPlannerDetailId())
                .dayNumber(plannerDetail.getDayNumber())
                .placeId(plannerDetail.getPlace().getPlaceId())
                .image(plannerDetail.getPlace().getImage())
                .placeName(plannerDetail.getPlace().getPlaceName())
                .addressName(plannerDetail.getPlace().getAddressName())
                .x(plannerDetail.getPlace().getX())
                .y(plannerDetail.getPlace().getY())
                .build();
    }

}
