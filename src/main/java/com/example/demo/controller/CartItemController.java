package com.example.demo.controller;

import com.example.demo.model.CartItem;
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
    public CartItem addItem(@RequestBody CartItem item) {
        return cartItemService.addItemToCart(item);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItemsForCart(@PathVariable Long cartId) {
        return cartItemService.getItemsForCart(cartId);
    }
}
