package com.example.food.service;

import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface SearchService {
    ResponseEntity searchPlace() throws UnsupportedEncodingException, URISyntaxException;
    ResponseEntity searchPlaces(String query, int categoryCode, int page);
}
