package com.foodOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRestaurantRestaurantId(Long restaurantId);

    // Get all reviews by a user
    List<Review> findByUserUserId(Long userId);

}
