package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;

import java.util.List;

public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository bundleRuleRepository;

    public BundleRuleServiceImpl(BundleRuleRepository bundleRuleRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        validateDiscount(rule);
        return bundleRuleRepository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        validateDiscount(rule);
        rule.setId(id);
        return bundleRuleRepository.save(rule);
    }

    @Override
    public BundleRule getRule(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BundleRule not found"));
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }

    @Override
    public void deactivateRule(Long id) {
        BundleRule rule = getRule(id);
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }

    private void validateDiscount(BundleRule rule) {
        if (rule.getDiscountPercentage() <= 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 1 and 100");
        }
    }
}
