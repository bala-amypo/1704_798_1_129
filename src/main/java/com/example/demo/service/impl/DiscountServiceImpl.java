package com.example.demo.service.impl;

import com.example.demo.entity.BundleRule;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final CartItemRepository cartItemRepository;
    private final BundleRuleRepository bundleRuleRepository;
    private final DiscountApplicationRepository discountRepo;

    public DiscountServiceImpl(
            CartItemRepository cartItemRepository,
            BundleRuleRepository bundleRuleRepository,
            DiscountApplicationRepository discountRepo) {
        this.cartItemRepository = cartItemRepository;
        this.bundleRuleRepository = bundleRuleRepository;
        this.discountRepo = discountRepo;
    }

    @Override
    public BigDecimal applyBundleDiscount(Long cartId) {

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        for (BundleRule rule : rules) {
            if (rule.getDiscountPercentage() <= 0 || rule.getDiscountPercentage() > 100) {
                throw new IllegalArgumentException("Invalid discount");
            }

            BigDecimal discount = total
                    .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                    .divide(BigDecimal.valueOf(100));

            BigDecimal finalPrice = total.subtract(discount);

            DiscountApplication da = new DiscountApplication();
            da.setCartId(cartId);
            da.setBundleRuleId(rule.getId());
            da.setDiscountAmount(discount);
            da.setFinalPrice(finalPrice);

            discountRepo.save(da);
            return finalPrice;
        }

        return total;
    }
}
