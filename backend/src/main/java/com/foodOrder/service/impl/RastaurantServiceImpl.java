package com.foodOrder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.entity.MenuItem;
import com.foodOrder.entity.Restaurant;
import com.foodOrder.repository.MenuItemRepository;
import com.foodOrder.repository.RestaurantRepository;
import com.foodOrder.service.RestaurantService;

@Service
public class RastaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAllRestourant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);
        if (existingRestaurant != null) {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setDescription(restaurant.getDescription());
            existingRestaurant.setLocation(restaurant.getLocation());
            existingRestaurant.setContactNumber(restaurant.getContactNumber());
            existingRestaurant.setRating(restaurant.getRating());
            existingRestaurant.setIsActive(restaurant.getIsActive());
            existingRestaurant.setCreatedAt(restaurant.getCreatedAt());
            existingRestaurant.setOwner(restaurant.getOwner());
            existingRestaurant.setMenuItems(restaurant.getMenuItems());
            existingRestaurant.setOrders(restaurant.getOrders());
            existingRestaurant.setReviews(restaurant.getReviews());
            return restaurantRepository.save(existingRestaurant);
        }
        return null;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<MenuItem> getAllMenuItems(Long restaurantId) {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long restaurantId, Long itemId) {
        return menuItemRepository.findById(itemId).orElse(null);
    }

    @Override
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long restaurantId, Long itemId, MenuItem menuItem) {
        MenuItem existingMenuItem = menuItemRepository.findById(itemId).orElse(null);
        if (existingMenuItem != null) {
            existingMenuItem.setName(menuItem.getName());
            existingMenuItem.setDescription(menuItem.getDescription());
            existingMenuItem.setPrice(menuItem.getPrice());
            existingMenuItem.setCategory(menuItem.getCategory());
            existingMenuItem.setIsAvailable(menuItem.getIsAvailable());
            existingMenuItem.setRestaurant(menuItem.getRestaurant());
            return menuItemRepository.save(existingMenuItem);
        }
        return null;
    }

    @Override
    public void deleteMenuItem(Long restaurantId, Long itemId) {
        menuItemRepository.deleteById(itemId);
    }

}
