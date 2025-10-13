package com.foodOrder.service;

import java.util.Optional;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.CartItem;

public interface CartService {

    Optional<Cart> getCartByUser(Long userId);

    CartItem addItemToCart(Long userId, CartItem cartItem);

    CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem);

    void deleteCartItem(Long userId, Long cartItemId);

    void clearCart(Long userId);

}
