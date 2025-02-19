package com.example.food.dto.response.user;

import com.example.food.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserInfoResponseDto {

    private String userId;
    private String nickname;
    private String profileImage;
    private String provider;
    private Date regDate;
    private Date updateDate;

    public static GetUserInfoResponseDto of(User user){
        return GetUserInfoResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .provider(user.getProvider())
                .regDate(user.getRegDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

}
