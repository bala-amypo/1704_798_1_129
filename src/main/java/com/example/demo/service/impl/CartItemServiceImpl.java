package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addItem(CartItem item) {
        Cart cart = item.getCart();
        if (!cart.getActive()) {
            throw new RuntimeException("Cart is inactive");
        }
        return cartItemRepository.save(item);
    }
}
