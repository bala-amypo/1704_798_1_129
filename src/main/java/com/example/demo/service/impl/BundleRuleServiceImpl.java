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
    public BundleRule create(BundleRule bundleRule) {
        validateDiscount(bundleRule);
        return bundleRuleRepository.save(bundleRule);
    }

    @Override
    public BundleRule update(Long id, BundleRule bundleRule) {
        validateDiscount(bundleRule);
        bundleRule.setId(id);
        return bundleRuleRepository.save(bundleRule);
    }

    @Override
    public BundleRule getById(Long id) {
        return bundleRuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BundleRule not found"));
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        BundleRule rule = getById(id);
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }

    private void validateDiscount(BundleRule bundleRule) {
        if (bundleRule.getDiscountPercentage() <= 0 ||
            bundleRule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 1 and 100");
        }
    }
}
