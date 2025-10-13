package com.foodOrder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.Order;
import com.foodOrder.entity.Review;
import com.foodOrder.entity.User;
import com.foodOrder.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable Long userid) {
        return userService.getUserById(userid);
    }

    @PutMapping("/{userid}")
    public String updateUser(@PathVariable Long userid, @RequestBody User user) {
        userService.updateUser(userid, user);
        return "Updated user with ID: " + userid;
    }

    @DeleteMapping("/{userid}")
    public String deleteUser(@PathVariable Long userid) {
        userService.deleteUser(userid);
        return "Deleted user with ID: " + userid;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{userid}/orders")
    public List<Order> getUserOrders(@PathVariable Long userid) {
        return userService.getUserOrders(userid);
    }

    @GetMapping("/{userid}/carts")
    public Cart getUserCart(@PathVariable Long userid) {
        return userService.getUserCart(userid);
    }

    @GetMapping("/{userid}/reviews")
    public List<Review> getUserReviews(@PathVariable Long userid) {
        return userService.getUserReviews(userid);
    }
}
