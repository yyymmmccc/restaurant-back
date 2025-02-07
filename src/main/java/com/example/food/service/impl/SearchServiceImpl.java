package com.example.food.service.impl;

import com.example.food.domain.Place;
import com.example.food.dto.response.search.SearchResultResponseDto;
import com.example.food.dto.response.ResponseDto;
import com.example.food.dto.response.search.PlaceResponseDto;
import com.example.food.dto.response.search.SearchResponseDto;
import com.example.food.repository.PlaceRepository;
import com.example.food.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Value("${kakao-api-key}")
    private String apiKey;

    @Value("${data-api-key}")
    private String dataApiKey;

    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public ResponseEntity searchPlace() throws UnsupportedEncodingException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";

        List<Place> list = new ArrayList<>();

        for(int i = 1; i <= 25; ++i){
            String indexNum = String.valueOf(i);
            String requestUrl = "?pageNo=" + indexNum + "&MobileOS=ETC&MobileApp=AppTest&_type=json&pageNo=1&contentTypeId=32&areaCode=39&ServiceKey=" + dataApiKey;
            URI uri = new URI(baseUrl + requestUrl);

            PlaceResponseDto result = restTemplate.getForEntity(uri, PlaceResponseDto.class).getBody();
            List<PlaceResponseDto.Item> itemList = result.getResponse().getBody().getItems().getItem();

            for(PlaceResponseDto.Item item : itemList){
                Place place = Place.builder()
                        .categoryCode(Integer.parseInt(item.getContenttypeid()))
                        .areaCode(Integer.parseInt(item.getAreacode()))
                        .placeName(item.getTitle())
                        .phone(item.getTel())
                        .addressName(item.getAddr1())
                        .x(Double.parseDouble(item.getMapx()))
                        .y(Double.parseDouble(item.getMapy()))
                        .image(item.getFirstimage())
                        .build();

                placeRepository.save(place);
                //list.add(place);
                log.info(item.getTitle() + " 저장완료");
            }
        }
        return ResponseDto.success(null);
    }

    @Override
    public ResponseEntity searchPlaces(String query, int categoryCode, int page) {

        Page<Place> placeList = placeRepository.findByPlace(query, categoryCode, calcPage(page));

        List<SearchResponseDto> list = new ArrayList<>();

        for(Place place : placeList){
            list.add(SearchResponseDto.of(place));
        }

        return ResponseDto.success(new SearchResultResponseDto(placeList.getTotalPages(), placeList.getTotalElements(), list));
    }

    public Pageable calcPage(int page){
        return PageRequest.of(page <= 0 ? 0 : page - 1, 10);


    }
}
