package com.example.food.repository;

import com.example.food.domain.User;
import com.example.food.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    boolean existsByUserAndPlaceId(User user, String placeId);

    List<Wishlist> findByUser(User user);
}
