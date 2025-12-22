package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @PostMapping
    public String addItem() {
        return "Add item to cart";
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable Long id) {
        return "Update cart item " + id;
    }

    @GetMapping("/cart/{cartId}")
    public String getItemsByCart(@PathVariable Long cartId) {
        return "List items for cart " + cartId;
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        return "Remove cart item " + id;
    }
}
