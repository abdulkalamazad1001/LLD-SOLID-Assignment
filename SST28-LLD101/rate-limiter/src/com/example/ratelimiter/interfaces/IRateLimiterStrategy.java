package com.example.ratelimiter.interfaces;

public interface IRateLimiterStrategy {
    boolean isAllowed(String key);
}
