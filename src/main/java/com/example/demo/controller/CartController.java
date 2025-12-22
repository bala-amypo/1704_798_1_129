package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @PostMapping("/{userId}")
    public String createCart(@PathVariable Long userId) {
        return "Create cart for user " + userId;
    }

    @GetMapping("/{id}")
    public String getCart(@PathVariable Long id) {
        return "Get cart " + id;
    }

    @GetMapping("/user/{userId}")
    public String getCartByUser(@PathVariable Long userId) {
        return "Get cart for user " + userId;
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateCart(@PathVariable Long id) {
        return "Deactivate cart " + id;
    }
}
