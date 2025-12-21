package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.service.impl.CartItemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemServiceImpl cartItemService;

    public CartItemController(CartItemServiceImpl cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem create(@RequestBody CartItem item) {
        return cartItemService.save(item);
    }

    @GetMapping
    public List<CartItem> getAll() {
        return cartItemService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartItemService.deleteById(id);
    }
}
