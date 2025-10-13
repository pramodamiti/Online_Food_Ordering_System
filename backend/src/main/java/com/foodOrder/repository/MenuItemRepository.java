package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    
}
