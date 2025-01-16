package com.example.food.service;

import com.example.food.dto.request.auth.SignUpRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface AuthService {
    ResponseEntity registerUser(SignUpRequestDto dto);
}
