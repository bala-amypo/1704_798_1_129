package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Long userId) {
        Cart cart = cartService.getActiveCartForUser(userId);
        return ResponseEntity.ok(cart);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCart(@PathVariable Long id) {
        cartService.deactivateCart(id);
        return ResponseEntity.ok().build();
    }
}