package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
