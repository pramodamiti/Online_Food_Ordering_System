package com.foodOrder.service;

import java.util.List;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.Order;
import com.foodOrder.entity.Review;
import com.foodOrder.entity.User;

public interface UserService {

    User getUserById(Long userid);

    void updateUser(Long userid, User user);

    void deleteUser(Long userid);

    List<User> getAllUser();

    List<Order> getUserOrders(Long userid);

    Cart getUserCart(Long userid);

    List<Review> getUserReviews(Long userid);
}
