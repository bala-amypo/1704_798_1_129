package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }
}
