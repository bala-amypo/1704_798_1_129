package com.example.demo.controller;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bundle-rules")
public class BundleRuleController {
    
    @Autowired
    private BundleRuleService bundleRuleService;
    
    @PostMapping
    public ResponseEntity<BundleRule> createRule(@RequestBody BundleRule rule) {
        BundleRule created = bundleRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BundleRule> updateRule(@PathVariable Long id, @RequestBody BundleRule rule) {
        BundleRule updated = bundleRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BundleRule> getRule(@PathVariable Long id) {
        BundleRule rule = bundleRuleService.getRuleById(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<BundleRule>> listActiveRules() {
        List<BundleRule> rules = bundleRuleService.getActiveRules();
        return ResponseEntity.ok(rules);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        bundleRuleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}