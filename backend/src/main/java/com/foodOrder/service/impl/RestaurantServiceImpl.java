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
public class RestaurantServiceImpl implements RestaurantService {

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

    // -------------------- MenuItem Methods --------------------
    @Override
    public List<MenuItem> getAllMenuItems(Long restaurantId) {
        // Fetch only menu items for the specified restaurant
        return menuItemRepository.findByRestaurantRestaurantId(restaurantId);
    }

    @Override
    public MenuItem getMenuItemById(Long restaurantId, Long itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId).orElse(null);
        if (menuItem != null && menuItem.getRestaurant().getRestaurantId().equals(restaurantId)) {
            return menuItem;
        }
        return null;
    }

    @Override
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + restaurantId));
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(Long restaurantId, Long itemId, MenuItem menuItem) {
        MenuItem existingMenuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found: " + itemId));

        // Ensure menu item belongs to this restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + restaurantId));

        existingMenuItem.setName(menuItem.getName());
        existingMenuItem.setDescription(menuItem.getDescription());
        existingMenuItem.setPrice(menuItem.getPrice());
        existingMenuItem.setCategory(menuItem.getCategory());
        existingMenuItem.setIsAvailable(menuItem.getIsAvailable());
        existingMenuItem.setRestaurant(restaurant);

        return menuItemRepository.save(existingMenuItem);
    }

    @Override
    public void deleteMenuItem(Long restaurantId, Long itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found: " + itemId));

        // Make sure the menu item belongs to this restaurant
        if (!menuItem.getRestaurant().getRestaurantId().equals(restaurantId)) {
            throw new RuntimeException("Menu item does not belong to restaurant: " + restaurantId);
        }

        menuItemRepository.deleteById(itemId);
    }

}
