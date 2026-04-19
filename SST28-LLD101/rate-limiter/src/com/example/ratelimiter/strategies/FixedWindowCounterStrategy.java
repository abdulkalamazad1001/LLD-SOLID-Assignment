package com.example.ratelimiter.strategies;

import com.example.ratelimiter.interfaces.IRateLimiterStrategy;
import com.example.ratelimiter.interfaces.IWindowConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounterStrategy implements IRateLimiterStrategy {

    private static class WindowState {
        private final long windowStart;
        private final AtomicInteger count;

        public WindowState(long windowStart, int count) {
            this.windowStart = windowStart;
            this.count = new AtomicInteger(count);
        }

        public long getWindowStart() {
            return windowStart;
        }

        public AtomicInteger getCount() {
            return count;
        }
    }

    private final IWindowConfig config;
    private final ConcurrentHashMap<String, WindowState> limits;

    public FixedWindowCounterStrategy(IWindowConfig config) {
        this.config = config;
        this.limits = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isAllowed(String key) {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - (currentTime % config.getWindowSizeInMillis());

        WindowState finalState = limits.compute(key, (k, existingState) -> {
            if (existingState == null || existingState.getWindowStart() != windowStart) {
                return new WindowState(windowStart, 1);
            }
            existingState.getCount().incrementAndGet();
            return existingState;
        });

        return finalState.getCount().get() <= config.getMaxRequests();
    }
}
