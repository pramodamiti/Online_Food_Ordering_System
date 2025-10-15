package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
