package com.example.food.service;

import com.example.food.dto.request.user.UpdateUserNicknameRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {


    ResponseEntity getUserInfo(String userId);

    boolean checkNicknameDuplicate(String nickname);

    ResponseEntity dupNickname(String nickname);

    ResponseEntity updateNickname(String userId, UpdateUserNicknameRequestDto dto);
}
