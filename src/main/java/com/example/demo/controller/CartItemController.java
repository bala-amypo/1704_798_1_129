package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    
    @Autowired
    private CartItemService cartItemService;
    
    @PostMapping
    public ResponseEntity<CartItem> addItemToCart(@RequestBody CartItem cartItem) {
        CartItem added = cartItemService.addItemToCart(cartItem);
        return ResponseEntity.ok(added);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        CartItem updated = cartItemService.updateCartItem(id, cartItem);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItem>> listItems(@PathVariable Long cartId) {
        List<CartItem> items = cartItemService.getItemsForCart(cartId);
        return ResponseEntity.ok(items);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartItemService.removeCartItem(id);
        return ResponseEntity.ok().build();
    }
}