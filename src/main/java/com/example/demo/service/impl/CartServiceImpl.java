package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setActive(true);
        return cartRepository.save(cart);
    }

    // ✅ THIS FIXES ERROR #2
    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }

    @Override
    public Cart getActiveCartForUser(Long userId) {
        return cartRepository.findByUserIdAndActiveTrue(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Active cart not found for user"));
    }

    @Override
    public void deactivateCart(Long cartId) {
        cartRepository.findById(cartId).ifPresent(cart -> {
            cart.setActive(false);
            cartRepository.save(cart);
        });
    }
}
