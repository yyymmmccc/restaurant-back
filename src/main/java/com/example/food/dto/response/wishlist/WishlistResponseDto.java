package com.example.food.dto.response.wishlist;

import com.example.food.domain.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistResponseDto {

    private int wishlistId;
    private String placeName;
    private String placeId;
    private String roadAddressName;
    private String x;
    private String y;
    private Date createDate;
    private String userId;

    public static WishlistResponseDto of(Wishlist wishlist){

        return WishlistResponseDto.builder()
                .wishlistId(wishlist.getWishlistId())
                .placeName(wishlist.getPlaceName())
                .placeId(wishlist.getPlaceId())
                .roadAddressName(wishlist.getRoadAddressName())
                .x(wishlist.getX())
                .y(wishlist.getY())
                .createDate(wishlist.getCreateDate())
                .userId(wishlist.getUser().getUserId())
                .build();
    }
}
