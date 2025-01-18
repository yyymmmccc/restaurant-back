package com.example.food.dto.request.wishlist;

import com.example.food.domain.User;
import com.example.food.domain.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequestDto {

    private String placeId;
    private String placeName;
    private String roadAddressName;
    private String x;
    private String y;

    public Wishlist toEntity(User user) {
        return Wishlist.builder()
                .placeId(placeId)
                .placeName(placeName)
                .roadAddressName(roadAddressName)
                .x(x)
                .y(y)
                .user(user)
                .build();
    }
}
