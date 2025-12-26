package com.example.demo.repository;

import com.example.demo.model.Cart;
import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findById(Long id);

  
    Optional<Cart> findByUserIdAndActiveTrue(long userId);
}
