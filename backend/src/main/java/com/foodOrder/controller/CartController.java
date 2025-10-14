package com.foodOrder.controller;

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

    // Get the user's cart
    @GetMapping
    public Cart getCartByUser(@PathVariable Long userId) {
        return cartService.getCartByUser(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
    }

    // Add an item to the user's cart
    @PostMapping("/items")
    public CartItem addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(userId, cartItem);
    }

    // Update an existing cart item
    @PutMapping("/items/{cartItemId}")
    public CartItem updateCartItem(@PathVariable Long userId,
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem) {
        CartItem updatedItem = cartService.updateCartItem(userId, cartItemId, cartItem);
        if (updatedItem == null) {
            throw new RuntimeException("Cart item not found: " + cartItemId);
        }
        return updatedItem;
    }

    // Delete a specific cart item
    @DeleteMapping("/items/{cartItemId}")
    public String deleteCartItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.deleteCartItem(userId, cartItemId);
        return "Cart item removed successfully";
    }

    // Clear the user's cart
    @DeleteMapping
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully";
    }
}
