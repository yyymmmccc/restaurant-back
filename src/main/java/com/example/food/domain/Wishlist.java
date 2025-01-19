package com.example.food.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "wishlist")
@Table(name = "wishlist")
@Builder
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "road_address_name")
    private String roadAddressName;

    private String x;

    private String y;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

}
