package com.example.food.controller;

import com.example.food.dto.request.wishlist.WishlistRequestDto;
import com.example.food.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
@Slf4j
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("")
    public ResponseEntity createWishlist(@AuthenticationPrincipal String userId,
                                         @RequestBody @Valid WishlistRequestDto dto){

        return wishlistService.createWishlist(userId, dto);
    }

}
