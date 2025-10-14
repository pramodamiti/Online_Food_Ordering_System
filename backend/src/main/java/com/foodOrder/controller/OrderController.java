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

import com.foodOrder.entity.Order;
import com.foodOrder.entity.OrderItem;
import com.foodOrder.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ---------------- Orders ----------------
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        return orderService.getOrdersByRestaurant(restaurantId);
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return "Order deleted successfully";
    }

    // ---------------- OrderItems ----------------
    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }

    @PostMapping("/{orderId}/items")
    public OrderItem addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        return orderService.addOrderItem(orderId, orderItem);
    }

    @PutMapping("/{orderId}/items/{orderItemId}")
    public OrderItem updateOrderItem(@PathVariable Long orderId,
            @PathVariable Long orderItemId,
            @RequestBody OrderItem orderItem) {
        return orderService.updateOrderItem(orderId, orderItemId, orderItem);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public String deleteOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        orderService.deleteOrderItem(orderId, orderItemId);
        return "Order item deleted successfully";
    }
}
