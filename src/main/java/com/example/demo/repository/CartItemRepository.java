package com.example.demo.repository;

import com.example.demo.model.CartItem;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findById(Long id);

    List<CartItem> findByCartId(Long cartId);

    List<CartItem> findByCartIdAndMinQuantity(long cartId, int minQuantity);

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    void delete(CartItem cartItem);
}
