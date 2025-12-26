package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public CartItem addItemToCart(CartItem cartItem) {
        if (cartItem == null || cartItem.getCart() == null || cartItem.getProduct() == null) {
            throw new IllegalArgumentException("CartItem, Cart, and Product cannot be null");
        }
        
        if (cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        Cart cart = cartRepository.findById(cartItem.getCart().getId())
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        
        Product product = productRepository.findById(cartItem.getProduct().getId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        
        if (!product.getActive()) {
            throw new IllegalArgumentException("Product not available");
        }
        
        Optional<CartItem> existing = cartItemRepository.findByCartIdAndProductId(
            cartItem.getCart().getId(), cartItem.getProduct().getId());
        
        if (existing.isPresent()) {
            CartItem existingItem = existing.get();
            existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingItem);
        } else {
            return cartItemRepository.save(cartItem);
        }
    }
    
    public List<CartItem> getItemsForCart(Long cartId) {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        return cartItemRepository.findByCartId(cartId);
    }
    
    public CartItem updateCartItem(Long id, CartItem cartItem) {
        CartItem existing = cartItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        
        if (cartItem.getQuantity() != null) {
            if (cartItem.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            existing.setQuantity(cartItem.getQuantity());
        }
        
        return cartItemRepository.save(existing);
    }
    
    public void removeCartItem(Long id) {
        CartItem item = cartItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        cartItemRepository.delete(item);
    }
}