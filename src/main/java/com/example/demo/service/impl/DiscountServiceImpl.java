package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    
    @Autowired
    private DiscountApplicationRepository discountApplicationRepository;
    
    @Autowired
    private BundleRuleRepository bundleRuleRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Transactional
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return Collections.emptyList();
        }
        
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        List<BundleRule> activeRules = bundleRuleRepository.findByActiveTrue();
        
        discountApplicationRepository.deleteByCartId(cartId);
        
        List<DiscountApplication> applications = new ArrayList<>();
        
        for (BundleRule rule : activeRules) {
            if (isRuleApplicable(rule, cartItems)) {
                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(calculateDiscount(rule, cartItems));
                applications.add(discountApplicationRepository.save(app));
            }
        }
        
        return applications;
    }
    
    private boolean isRuleApplicable(BundleRule rule, List<CartItem> cartItems) {
        if (rule.getRequiredProductIds() == null || rule.getRequiredProductIds().trim().isEmpty()) {
            return false;
        }
        
        String[] requiredIds = rule.getRequiredProductIds().split(",");
        Set<Long> cartProductIds = cartItems.stream()
            .filter(item -> item.getProduct() != null)
            .map(item -> item.getProduct().getId())
            .collect(Collectors.toSet());
        
        for (String idStr : requiredIds) {
            try {
                Long requiredId = Long.parseLong(idStr.trim());
                if (!cartProductIds.contains(requiredId)) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    private BigDecimal calculateDiscount(BundleRule rule, List<CartItem> cartItems) {
        if (rule.getDiscountPercentage() == null) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal total = cartItems.stream()
            .filter(item -> item.getProduct() != null && item.getProduct().getPrice() != null)
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage()).divide(BigDecimal.valueOf(100)));
    }
    
    public DiscountApplication getDiscountById(Long id) {
        return discountApplicationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Discount application not found"));
    }
    
    public List<DiscountApplication> getDiscountsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}