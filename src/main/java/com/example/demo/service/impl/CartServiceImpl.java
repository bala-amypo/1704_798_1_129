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
    public Cart createCartForUser(Long userId) {
        Cart cart = new Cart(userId);
        cart.setActive(true);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getActiveCartForUser(Long userId) {
        return cartRepository
                .findByUserIdAndActiveTrue(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart(userId);
                    newCart.setActive(true);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.findById(cartId)
                .ifPresent(cartRepository::delete);
    }
}
