package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {
    
    @Autowired
    private BundleRuleRepository bundleRuleRepository;
    
    public BundleRule createRule(BundleRule rule) {
        if (rule.getDiscountPercentage() == null || rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Invalid discount percentage");
        }
        
        if (rule.getRequiredProductIds() == null || rule.getRequiredProductIds().trim().isEmpty()) {
            throw new IllegalArgumentException("Required product IDs cannot be empty");
        }
        
        return bundleRuleRepository.save(rule);
    }
    
    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule existing = bundleRuleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Bundle rule not found"));
        
        if (rule.getDiscountPercentage() != null) {
            if (rule.getDiscountPercentage() < 0 || rule.getDiscountPercentage() > 100) {
                throw new IllegalArgumentException("Invalid discount percentage");
            }
            existing.setDiscountPercentage(rule.getDiscountPercentage());
        }
        
        if (rule.getRuleName() != null) {
            existing.setRuleName(rule.getRuleName());
        }
        if (rule.getRequiredProductIds() != null) {
            if (rule.getRequiredProductIds().trim().isEmpty()) {
                throw new IllegalArgumentException("Required product IDs cannot be empty");
            }
            existing.setRequiredProductIds(rule.getRequiredProductIds());
        }
        
        return bundleRuleRepository.save(existing);
    }
    
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Bundle rule not found"));
    }
    
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActiveTrue();
    }
    
    public void deactivateRule(Long id) {
        BundleRule rule = getRuleById(id);
        rule.setActive(false);
        bundleRuleRepository.save(rule);
    }
}