package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.User;
import com.example.food.dto.request.auth.IdCheckRequestDto;
import com.example.food.dto.request.auth.LoginRequestDto;
import com.example.food.dto.request.auth.NicknameCheckRequestDto;
import com.example.food.dto.request.auth.SignUpRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.provider.JwtProvider;
import com.example.food.repository.UserRepository;
import com.example.food.service.AuthService;
import com.example.food.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity dupUserId(IdCheckRequestDto dto) {

        if(checkUserIdDuplicate(dto.getUserId()))
            throw new CustomException(ResponseCode.DUP_USER_ID);

        return ResponseDto.success(null);
    }
    @Override
    public ResponseEntity dupNickname(NicknameCheckRequestDto dto) {

        if(userService.checkNicknameDuplicate(dto.getNickname()))
            throw new CustomException(ResponseCode.DUP_NICKNAME);

        return ResponseDto.success(null);
    }

    @Override
    public ResponseEntity registerUser(SignUpRequestDto dto) {

        if(checkUserIdDuplicate(dto.getUserId()))
            throw new CustomException(ResponseCode.DUP_USER_ID);

        String password = dto.getPassword();

        if(!password.equals(dto.getPasswordCheck()))
            throw new CustomException(ResponseCode.REG_PASSWORD_FAIL);

        if(userService.checkNicknameDuplicate(dto.getNickname()))
            throw new CustomException(ResponseCode.DUP_NICKNAME);

        String encodedPassword = passwordEncoder.encode(password);

        User user = dto.toEntity(encodedPassword);
        userRepository.save(user);

        return ResponseDto.success(null);
    }

    @Override
    public ResponseEntity loginUser(LoginRequestDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(()
                -> new CustomException(ResponseCode.LOGIN_FAIL));

        boolean loginPasswordCheck = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!loginPasswordCheck)
            throw new CustomException(ResponseCode.LOGIN_FAIL);

        String accessToken = jwtProvider.createAccessToken(user.getUserId());

        return ResponseDto.success(accessToken);
    }

    public boolean checkUserIdDuplicate(String userId) {

        return userRepository.existsById(userId);
    }
}
