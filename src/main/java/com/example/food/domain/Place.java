package com.example.food.domain;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int placeId;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "area_code")
    private int areaCode;

    @Column(name = "place_name")
    private String placeName;

    private String phone;

    @Column(name = "address_name")
    private String addressName;

    private double x;
    private double y;

    private String image;
}
