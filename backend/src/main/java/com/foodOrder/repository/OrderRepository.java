package com.foodOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserUserId(Long userId);

    List<Order> findByRestaurantRestaurantId(Long restaurantId);

}
