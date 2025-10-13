package com.foodOrder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.entity.MenuItem;
import com.foodOrder.entity.Restaurant;
import com.foodOrder.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {

        return restaurantService.updateRestaurant(id, restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRestourant() {
        return restaurantService.getAllRestourant();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return "Deleted restaurant with ID: " + id;
    }

    // Get all menu items for a restaurant
    @GetMapping("/{restaurantId}/menu-items")
    public List<MenuItem> getAllMenuItems(@PathVariable Long restaurantId) {
        return restaurantService.getAllMenuItems(restaurantId);

    }

// Get a single menu item by ID
    @GetMapping("/{restaurantId}/menu-items/{itemId}")
    public MenuItem getMenuItemById(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        return restaurantService.getMenuItemById(restaurantId, itemId);

    }

// Add a new menu item to a restaurant
    @PostMapping("/{restaurantId}/menu-items")
    public MenuItem addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItem menuItem) {
        return restaurantService.addMenuItem(restaurantId, menuItem);
    }

// Update a menu item
    @PutMapping("/{restaurantId}/menu-items/{itemId}")
    public MenuItem updateMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId, @RequestBody MenuItem menuItem) {
        return restaurantService.updateMenuItem(restaurantId, itemId, menuItem);
    }

// Delete a menu item
    @DeleteMapping("/{restaurantId}/menu-items/{itemId}")
    public String deleteMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        restaurantService.deleteMenuItem(restaurantId, itemId);
        return "Deleted menu item with ID: " + itemId + " from restaurant with ID: " + restaurantId;
    }

}
