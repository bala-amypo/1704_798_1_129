package com.example.demo.service;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemService {
    CartItem addItemToCart(CartItem cartItem);
    CartItem updateCartItem(Long id, CartItem cartItem);
    List<CartItem> getItemsForCart(Long cartId);
    void removeCartItem(Long id);
}