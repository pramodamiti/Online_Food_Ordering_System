package com.foodOrder.service;

import java.util.List;

import com.foodOrder.entity.Review;

public interface ReviewService {

    Review addReview(Review review);

    List<Review> getAllReviews();

    List<Review> getReviewsByRestaurant(Long restaurantId);

    List<Review> getReviewsByUser(Long userId);

    void deleteReview(Long reviewId);
}
