package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
