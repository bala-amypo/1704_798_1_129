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
    public CartItem addItem(Long cartId, Long productId, int quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        if (!cart.getActive()) {
            throw new IllegalArgumentException("Cannot add items to inactive cart");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItem item = cartItemRepository
                .findByCartIdAndProductId(cartId, productId)
                .orElse(null);

        if (item == null) {
            item = new CartItem(); 
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        return cartItemRepository.save(item);
    }

    
    @Override
    public List<CartItem> getItemsForCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void removeCartItem(Long itemId) {
        cartItemRepository.findById(itemId)
                .ifPresent(cartItemRepository::delete);
    }
}
