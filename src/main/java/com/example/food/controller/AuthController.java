package com.example.food.controller;

import com.example.food.dto.request.auth.IdCheckRequestDto;
import com.example.food.dto.request.auth.LoginRequestDto;
import com.example.food.dto.request.auth.NicknameCheckRequestDto;
import com.example.food.dto.request.auth.SignUpRequestDto;
import com.example.food.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/check-id")
    public ResponseEntity dupUserId(@RequestBody IdCheckRequestDto dto){

        return authService.dupUserId(dto);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity dupNickname(@RequestBody NicknameCheckRequestDto dto){

        return authService.dupNickname(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody SignUpRequestDto dto){

        return authService.registerUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequestDto dto){

        return authService.loginUser(dto);
    }
}
