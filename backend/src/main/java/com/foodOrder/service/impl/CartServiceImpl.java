package com.foodOrder.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.entity.Cart;
import com.foodOrder.entity.CartItem;
import com.foodOrder.repository.CartItemRepository;
import com.foodOrder.repository.CartRepository;
import com.foodOrder.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Optional<Cart> getCartByUser(Long userId) {
        // Correctly fetch the cart by userId
        return cartRepository.findById(userId);
    }

    @Override
    public CartItem addItemToCart(Long userId, CartItem cartItem) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        // Link the cart item to the user's cart
        cartItem.setCart(cart);

        // Calculate subtotal if needed
        if (cartItem.getTotalPrice() != null && cartItem.getQuantity() != null) {
            cartItem.setSubtotal(cartItem.getTotalPrice() * cartItem.getQuantity());
        }

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(cartItem.getQuantity());
            if (cartItem.getTotalPrice() != null) {
                existingCartItem.setTotalPrice(cartItem.getTotalPrice());
            }
            if (existingCartItem.getTotalPrice() != null && existingCartItem.getQuantity() != null) {
                existingCartItem.setTotalPrice(existingCartItem.getTotalPrice() * existingCartItem.getQuantity());
            }
            return cartItemRepository.save(existingCartItem);
        }
        return null;
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
        if (cartItemOpt.isPresent() && cartItemOpt.get().getCart().getCartId().equals(cart.getCartId())) {
            cartItemRepository.deleteById(cartItemId);
        }
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        if (cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
            cartItemRepository.deleteAll(cart.getCartItems());
        }
    }
}
