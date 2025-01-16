package com.example.food.service.impl;

import com.example.food.domain.User;
import com.example.food.dto.request.auth.SignUpRequestDto;
import com.example.food.repository.UserRepository;
import com.example.food.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity registerUser(SignUpRequestDto dto) {

        User user = dto.toEntity();
        userRepository.save(user);

        return null;
    }
}
