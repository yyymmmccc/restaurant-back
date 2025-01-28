package com.example.food.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
@Entity(name = "place")
@Builder
public class Place {

    @Id
    @Column(name = "place_id")
    private int placeId;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "place_name")
    private String placeName;

    private String phone;

    @Column(name = "address_name")
    private String addressName;

    private double x;
    private double y;
}
