package com.example.ratelimiter.strategies;

import com.example.ratelimiter.interfaces.IRateLimiterStrategy;
import com.example.ratelimiter.interfaces.IWindowConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounterStrategy implements IRateLimiterStrategy {

    private static class SlidingState {
        private final AtomicInteger prevCount;
        private final AtomicInteger currentCount;
        private final long currentWindowStart;

        public SlidingState(long currentWindowStart, int prevCountVal, int currentCountVal) {
            this.currentWindowStart = currentWindowStart;
            this.prevCount = new AtomicInteger(prevCountVal);
            this.currentCount = new AtomicInteger(currentCountVal);
        }

        public long getCurrentWindowStart() {
            return currentWindowStart;
        }

        public AtomicInteger getPrevCount() {
            return prevCount;
        }

        public AtomicInteger getCurrentCount() {
            return currentCount;
        }
    }

    private final IWindowConfig config;
    private final ConcurrentHashMap<String, SlidingState> limits;

    public SlidingWindowCounterStrategy(IWindowConfig config) {
        this.config = config;
        this.limits = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isAllowed(String key) {
        long currentTime = System.currentTimeMillis();
        long windowSize = config.getWindowSizeInMillis();
        long currentWindowStart = currentTime - (currentTime % windowSize);

        SlidingState stateAfterAccess = limits.compute(key, (k, existingState) -> {
            if (existingState == null) {
                return new SlidingState(currentWindowStart, 0, 1);
            }

            if (existingState.getCurrentWindowStart() == currentWindowStart) {
                existingState.getCurrentCount().incrementAndGet();
                return existingState;
            }

            long diffWindows = (currentWindowStart - existingState.getCurrentWindowStart()) / windowSize;
            int newPrevCount = (diffWindows == 1) ? existingState.getCurrentCount().get() : 0;
            return new SlidingState(currentWindowStart, newPrevCount, 1);
        });

        double currentWindowElapsedFraction = (currentTime - currentWindowStart) / (double) windowSize;
        double prevWindowWeight = 1.0 - currentWindowElapsedFraction;

        double estimatedRequests = (stateAfterAccess.getPrevCount().get() * prevWindowWeight)
                + stateAfterAccess.getCurrentCount().get();

        if (estimatedRequests > config.getMaxRequests()) {
            stateAfterAccess.getCurrentCount().decrementAndGet();
            return false;
        }

        return true;
    }
}
