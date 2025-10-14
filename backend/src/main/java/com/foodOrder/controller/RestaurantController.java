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

    // -------------------- Restaurant CRUD --------------------
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/{restaurantId}")
    public Restaurant updateRestaurant(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurantId, restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestourant();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @DeleteMapping("/{restaurantId}")
    public String deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return "Deleted restaurant with ID: " + restaurantId;
    }

    // -------------------- MenuItem CRUD --------------------
    @GetMapping("/{restaurantId}/menu-items")
    public List<MenuItem> getAllMenuItems(@PathVariable Long restaurantId) {
        return restaurantService.getAllMenuItems(restaurantId);
    }

    @GetMapping("/{restaurantId}/menu-items/{itemId}")
    public MenuItem getMenuItemById(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        MenuItem menuItem = restaurantService.getMenuItemById(restaurantId, itemId);
        if (menuItem == null) {
            throw new RuntimeException("Menu item not found or does not belong to restaurant: " + restaurantId);
        }
        return menuItem;
    }

    @PostMapping("/{restaurantId}/menu-items")
    public MenuItem addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItem menuItem) {
        return restaurantService.addMenuItem(restaurantId, menuItem);
    }

    @PutMapping("/{restaurantId}/menu-items/{itemId}")
    public MenuItem updateMenuItem(@PathVariable Long restaurantId,
            @PathVariable Long itemId,
            @RequestBody MenuItem menuItem) {
        return restaurantService.updateMenuItem(restaurantId, itemId, menuItem);
    }

    @DeleteMapping("/{restaurantId}/menu-items/{itemId}")
    public String deleteMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        restaurantService.deleteMenuItem(restaurantId, itemId);
        return "Deleted menu item with ID: " + itemId + " from restaurant with ID: " + restaurantId;
    }
}
