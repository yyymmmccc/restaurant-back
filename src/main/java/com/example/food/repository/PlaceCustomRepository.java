package com.example.food.repository;

import com.example.food.domain.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.util.List;

public interface PlaceCustomRepository {

    Page<Place> findByPlace(String query, int categoryCode, Pageable pageable);
}
