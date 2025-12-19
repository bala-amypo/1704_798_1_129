package com.example.demo.service;

import java.math.BigDecimal;

public interface DiscountService {

    BigDecimal applyBundleDiscount(Long cartId);
}
