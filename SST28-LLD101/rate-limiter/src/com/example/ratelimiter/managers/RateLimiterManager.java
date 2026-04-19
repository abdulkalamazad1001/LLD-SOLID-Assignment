package com.example.ratelimiter.managers;

import com.example.ratelimiter.interfaces.IRateLimiterStrategy;

public class RateLimiterManager {
    private IRateLimiterStrategy strategy;

    public RateLimiterManager(IRateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IRateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkAndAllow(String key) {
        if (strategy == null) {
            return true; 
        }
        return strategy.isAllowed(key);
    }
}
