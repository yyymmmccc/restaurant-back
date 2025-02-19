package com.example.food.controller;

import com.example.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/my")
    public ResponseEntity getUserInfo(@AuthenticationPrincipal String userId){


        return userService.getUserInfo(userId);
    }

    @GetMapping("/nickname-check")
    public ResponseEntity dupNickname(@RequestParam(name = "nickname") String nickname){

        return userService.dupNickname(nickname);
    }

}
