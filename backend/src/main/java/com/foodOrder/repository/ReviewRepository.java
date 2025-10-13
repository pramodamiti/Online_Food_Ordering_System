package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Review;

public interface ReviewRepository extends  JpaRepository<Review, Long> {
    
}
