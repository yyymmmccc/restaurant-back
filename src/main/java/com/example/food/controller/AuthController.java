package com.example.food.controller;

import com.example.food.dto.request.auth.SignUpRequestDto;
import com.example.food.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody SignUpRequestDto dto){

        return authService.registerUser(dto);
    }
}
