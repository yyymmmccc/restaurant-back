package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.User;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.user.GetUserInfoResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.repository.UserRepository;
import com.example.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity getUserInfo(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_USER));

        return ResponseDto.success(GetUserInfoResponseDto.of(user));
    }

    @Override
    public ResponseEntity dupNickname(String nickname) {

        if(checkNicknameDuplicate(nickname))
            throw new CustomException(ResponseCode.DUP_NICKNAME);

        return ResponseDto.success(null);
    }

    public boolean checkNicknameDuplicate(String nickname) {

        return userRepository.existsByNickname(nickname);
    }
}
