package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bundle-rules")
public class BundleRuleController {

    @PostMapping
    public String createRule() {
        return "Create bundle rule";
    }

    @PutMapping("/{id}")
    public String updateRule(@PathVariable Long id) {
        return "Update bundle rule " + id;
    }

    @GetMapping("/{id}")
    public String getRule(@PathVariable Long id) {
        return "Get bundle rule " + id;
    }

    @GetMapping("/active")
    public String getActiveRules() {
        return "List active bundle rules";
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateRule(@PathVariable Long id) {
        return "Deactivate bundle rule " + id;
    }
}
