package com.example.food.dto.request.auth;

import com.example.food.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String userId;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String phoneNumber;

    public User toEntity(){
         return User.builder()
                 .userId(userId)
                 .password(password)
                 .nickname(nickname)
                 .phoneNumber(phoneNumber)
                 .provider("local")
                 .build();
    }

}
