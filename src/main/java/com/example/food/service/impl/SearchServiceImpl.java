package com.example.food.service.impl;

import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.search.RestaurantDto;
import com.example.food.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Value("${kakao-api-key}")
    private String apiKey;

    @Override
    public ResponseEntity searchRestaurant(String query, int page) {

        String url = "https://dapi.kakao.com/v2/local/search/keyword.json";

        WebClient webClient = WebClient.create(url);
        RestaurantDto result = webClient
                        .get()
                        .uri("?query=" + query + "&category_group_code=FD6&page=" + page)
                        .header("Authorization", "KakaoAK " + apiKey)
                        .retrieve()  // 응답처리
                        .bodyToMono(RestaurantDto.class) // 응답 본문을 String으로 받기
                        .block(); // 비동기는 subscribe()

        return ResponseDto.success(result.getDocuments());
    }
}
