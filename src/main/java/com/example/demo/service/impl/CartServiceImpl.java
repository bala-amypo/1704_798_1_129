package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    public Cart createCart(Long userId) {
        // Check if user already has a cart
        Optional<Cart> existingCart = cartRepository.findByUserId(userId);
        if (existingCart.isPresent()) {
            throw new IllegalArgumentException("Cart already exists for user");
        }
        
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }
    
    public Cart getActiveCartForUser(Long userId) {
        return cartRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));
    }
    
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }
    
    public void deactivateCart(Long id) {
        Cart cart = getCartById(id);
        // Since Cart model doesn't have active field, we delete the cart
        cartRepository.delete(cart);
    }
}