package com.example.food.service;

import org.springframework.http.ResponseEntity;

public interface UserService {


    ResponseEntity getUserInfo(String userId);

    boolean checkNicknameDuplicate(String nickname);

    ResponseEntity dupNickname(String nickname);
}
