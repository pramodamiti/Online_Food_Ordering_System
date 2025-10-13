package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
