package com.foodOrder.service;

import java.util.List;

import com.foodOrder.entity.MenuItem;
import com.foodOrder.entity.Restaurant;

public interface RestaurantService {

    void deleteRestaurant(Long id);

    Restaurant getRestaurantById(Long id);

    List<Restaurant> getAllRestourant();

    Restaurant updateRestaurant(Long id, Restaurant restaurant);

    Restaurant addRestaurant(Restaurant restaurant);

    List<MenuItem> getAllMenuItems(Long restaurantId);

    MenuItem getMenuItemById(Long restaurantId, Long itemId);

    MenuItem addMenuItem(Long restaurantId, MenuItem menuItem);

    MenuItem updateMenuItem(Long restaurantId, Long itemId, MenuItem menuItem);

    void deleteMenuItem(Long restaurantId, Long itemId);
    
}
