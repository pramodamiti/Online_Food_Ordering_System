package com.foodOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantRestaurantId(Long restaurantId);
}
