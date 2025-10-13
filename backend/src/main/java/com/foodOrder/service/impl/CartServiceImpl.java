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
        return cartRepository.findById(userId);
    }

    @Override
    public CartItem addItemToCart(Long userId, CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }
        return null;
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Long userId) {
        if (userId != null) {
            cartItemRepository.deleteAll(); 
        }else {
            System.out.println("User id is null");
        }
    }

}
