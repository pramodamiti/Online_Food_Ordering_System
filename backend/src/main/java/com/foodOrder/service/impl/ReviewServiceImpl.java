package com.foodOrder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.entity.Review;
import com.foodOrder.repository.ReviewRepository;
import com.foodOrder.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByRestaurant(Long restaurantId) {
        return reviewRepository.findByRestaurantRestaurantId(restaurantId);
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
