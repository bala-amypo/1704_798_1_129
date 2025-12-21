package com.example.demo.service.impl;

import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem save(CartItem item) {
        return cartItemRepository.save(item);
    }

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
