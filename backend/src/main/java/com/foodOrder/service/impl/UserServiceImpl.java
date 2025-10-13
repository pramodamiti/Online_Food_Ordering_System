package com.foodOrder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.Order;
import com.foodOrder.entity.Review;
import com.foodOrder.entity.User;
import com.foodOrder.repository.UserRepository;
import com.foodOrder.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userid) {
        return userRepository.findById(userid).orElse(null);
    }

    @Override
    public void updateUser(Long userid, User user) {
        user.setUserId(userid);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userid) {
        userRepository.deleteById(userid);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<Order> getUserOrders(Long userid) {
        User user = userRepository.findById(userid).orElse(null);
        return user != null ? user.getOrders() : null;
    }

    @Override
    public Cart getUserCart(Long userid) {
        User user = userRepository.findById(userid).orElse(null);
        return user != null ? user.getCart() : null;
    }

    @Override
    public List<Review> getUserReviews(Long userid) {
        User user = userRepository.findById(userid).orElse(null);
        return user != null ? user.getReviews() : null;
    }

}
