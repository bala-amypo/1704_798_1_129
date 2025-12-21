package com.example.demo.service.impl;

import com.example.demo.repository.DiscountApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl {

    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(DiscountApplicationRepository repo) {
        this.discountApplicationRepository = repo;
    }

    public void clearDiscountsForCart(Long cartId) {
        discountApplicationRepository.deleteByCartId(cartId);
    }
}
