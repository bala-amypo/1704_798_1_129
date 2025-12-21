package com.example.demo.service.impl;

import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.DiscountApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DiscountServiceImpl {

    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(DiscountApplicationRepository discountApplicationRepository) {
        this.discountApplicationRepository = discountApplicationRepository;
    }

    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        discountApplicationRepository.deleteByCartId(cartId);
        return Collections.emptyList(); // full logic comes later
    }
}
