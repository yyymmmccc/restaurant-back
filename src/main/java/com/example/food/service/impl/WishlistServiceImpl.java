package com.example.food.service.impl;

import com.example.food.common.ResponseCode;
import com.example.food.domain.User;
import com.example.food.domain.Wishlist;
import com.example.food.dto.request.wishlist.WishlistRequestDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.handler.CustomException;
import com.example.food.repository.UserRepository;
import com.example.food.repository.WishlistRepository;
import com.example.food.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity createWishlist(String userId, WishlistRequestDto dto) {

        User user = userRepository.findById(userId).orElseThrow(()
                -> new CustomException(ResponseCode.NOT_FOUND_USER));

        boolean existsByWishlist = wishlistRepository.existsByUserAndPlaceId(user, dto.getPlaceId());
        if(existsByWishlist)
            throw new CustomException(ResponseCode.DUP_PLACE);

        Wishlist wishlist = dto.toEntity(user);
        wishlistRepository.save(wishlist);

        return ResponseDto.success(null);
    }
}
