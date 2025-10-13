package com.foodOrder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.CartItem;
import com.foodOrder.service.CartService;

@RestController
@RequestMapping("/users/{userId}/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Optional<Cart> getCartByUser(@PathVariable Long userId) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/items")
    public CartItem addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(userId, cartItem);
    }

    @PutMapping("/items/{cartItemId}")
    public CartItem updateCartItem(@PathVariable Long userId, @PathVariable Long cartItemId, @RequestBody CartItem cartItem) {
        return cartService.updateCartItem(userId, cartItemId, cartItem);
    }

    @DeleteMapping("/items/{cartItemId}")
    public String deleteCartItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.deleteCartItem(userId, cartItemId);
        return "Cart item removed successfully";
    }

    @DeleteMapping
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully";
    }
}
