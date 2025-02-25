package com.example.food.controller;

import com.example.food.dto.request.user.UpdateUserNicknameRequestDto;
import com.example.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/nickname")
    public ResponseEntity updateNickname(@AuthenticationPrincipal String userId,
                                         @RequestBody UpdateUserNicknameRequestDto dto){

        return userService.updateNickname(userId, dto);
    }

}
