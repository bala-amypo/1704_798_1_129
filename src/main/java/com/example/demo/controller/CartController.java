package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.service.impl.CartServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

    @GetMapping
    public List<Cart> getAll() {
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public Cart getById(@PathVariable Long id) {
        return cartService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartService.deleteById(id);
    }
}
