package com.foodOrder.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.entity.Order;
import com.foodOrder.entity.OrderItem;
import com.foodOrder.repository.OrderItemRepository;
import com.foodOrder.repository.OrderRepository;
import com.foodOrder.repository.UserRepository;
import com.foodOrder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserUserId(userId);
    }

    @Override
    public List<Order> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantRestaurantId(restaurantId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // ---------------- OrderItem CRUD ----------------
    @Override
    public OrderItem addOrderItem(Long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found: " + orderId));
        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long orderId, Long orderItemId, OrderItem orderItem) {
        OrderItem existing = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found: " + orderItemId));
        existing.setQuantity(orderItem.getQuantity());
        existing.setPrice(orderItem.getPrice());
        existing.setSubtotal(orderItem.getSubtotal());
        return orderItemRepository.save(existing);
    }

    @Override
    public void deleteOrderItem(Long orderId, Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        return order.getOrderItems();
    }
}
