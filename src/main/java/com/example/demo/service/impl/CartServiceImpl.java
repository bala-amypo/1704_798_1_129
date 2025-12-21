package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getActiveCartForUser(Long userId) {
        return cartRepository.findByUserIdAndActive(userId, true)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setActive(true);
                    return cartRepository.save(cart);
                });
    }
}
