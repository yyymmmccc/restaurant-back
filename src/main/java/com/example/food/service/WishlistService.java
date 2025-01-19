package com.example.food.service;

import com.example.food.dto.request.wishlist.WishlistRequestDto;
import org.springframework.http.ResponseEntity;

public interface WishlistService {
    ResponseEntity createWishlist(String userId, WishlistRequestDto dto);

    ResponseEntity showWishlist(String userId);

    ResponseEntity deleteWishlist(String userId, int wishlistId);
}
