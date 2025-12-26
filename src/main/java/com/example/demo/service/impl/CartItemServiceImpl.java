package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartItemService;

import java.util.List;

public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               CartRepository cartRepository,
                               ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

   
    @Override
    public CartItem addItemToCart(CartItem cartItem) {

        Long cartId = cartItem.getCart().getId();
        Long productId = cartItem.getProduct().getId();
        int quantity = cartItem.getQuantity();

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        if (!cart.getActive()) {
            throw new IllegalArgumentException("Cannot add items to inactive cart");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem existing = cartItemRepository
                .findByCartIdAndProductId(cartId, productId)
                .orElse(null);

        if (existing == null) {
            existing = new CartItem();
            existing.setCart(cart);
            existing.setProduct(product);
            existing.setQuantity(quantity);
        } else {
            existing.setQuantity(existing.getQuantity() + quantity);
        }

        return cartItemRepository.save(existing);
    }

    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public CartItem updateCartItem(Long id, CartItem updatedItem) {
        CartItem existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found"));

        existing.setQua
