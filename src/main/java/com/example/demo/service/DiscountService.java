package com.example.demo.service;

import com.example.demo.model.DiscountApplication;
import java.util.List;

public interface DiscountService {
    List<DiscountApplication> evaluateDiscounts(Long cartId);
    DiscountApplication getDiscountById(Long id);
    List<DiscountApplication> getDiscountsForCart(Long cartId);
}