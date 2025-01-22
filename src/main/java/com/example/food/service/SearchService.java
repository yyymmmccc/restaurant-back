package com.example.food.service;

import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity searchRestaurant(String query, String categoryGroupCode, int page);
}
