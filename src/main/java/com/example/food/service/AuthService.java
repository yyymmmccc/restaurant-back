package com.example.food.service;

import com.example.food.dto.request.auth.IdCheckRequestDto;
import com.example.food.dto.request.auth.LoginRequestDto;
import com.example.food.dto.request.auth.NicknameCheckRequestDto;
import com.example.food.dto.request.auth.SignUpRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity dupUserId(IdCheckRequestDto dto);

    ResponseEntity dupNickname(NicknameCheckRequestDto dto);

    ResponseEntity registerUser(SignUpRequestDto dto);

    ResponseEntity loginUser(LoginRequestDto dto);
}
