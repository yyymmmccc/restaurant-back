package com.example.food.controller;

import com.example.food.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final SearchService searchService;
    
    @GetMapping("/add")
    public ResponseEntity searchPlace() throws UnsupportedEncodingException, URISyntaxException {
        
        return searchService.searchPlace();
    }

    @GetMapping("")
    public ResponseEntity searchPlaces(@RequestParam(defaultValue = "") String query,
                                       @RequestParam(defaultValue = "0") int categoryCode,
                                       @RequestParam(defaultValue = "1") int page){
                                       //@PageableDefault(size = 10) Pageable pageable){

        return searchService.searchPlaces(query, categoryCode, page);
    }

}
