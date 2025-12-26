package com.example.demo.repository;

import com.example.demo.model.CartItem;
import java.util.List;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    List<CartItem> findByCartId(Long cartId);

    List<CartItem> findByCartIdAndMinQuantity(long cartId, int minQuantity);
}
