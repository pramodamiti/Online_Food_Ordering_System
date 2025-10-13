package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
