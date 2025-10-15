package com.foodOrder.service;

import java.util.List;

import com.foodOrder.entity.Order;
import com.foodOrder.entity.OrderItem;

public interface OrderService {

    Order createOrder(Order order); // Place new order

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(Long userId);

    List<Order> getOrdersByRestaurant(Long restaurantId);

    void deleteOrder(Long orderId);

    // OrderItem management
    OrderItem addOrderItem(Long orderId, OrderItem orderItem);

    OrderItem updateOrderItem(Long orderId, Long orderItemId, OrderItem orderItem);

    void deleteOrderItem(Long orderId, Long orderItemId);

    List<OrderItem> getOrderItems(Long orderId);
}
