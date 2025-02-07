package com.example.food.dto.response.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultResponseDto {

    private long totalPages;
    private long totalElements;
    private List<SearchResponseDto> items;
}
