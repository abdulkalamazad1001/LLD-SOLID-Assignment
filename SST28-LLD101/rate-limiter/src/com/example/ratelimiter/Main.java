package com.example.ratelimiter;

import com.example.ratelimiter.interfaces.IRateLimiterStrategy;
import com.example.ratelimiter.interfaces.IWindowConfig;
import com.example.ratelimiter.managers.RateLimiterManager;
import com.example.ratelimiter.models.BasicWindowConfig;
import com.example.ratelimiter.strategies.FixedWindowCounterStrategy;
import com.example.ratelimiter.strategies.SlidingWindowCounterStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Rate Limiter Module Simulation ---\n");

        IWindowConfig config = new BasicWindowConfig(5, 1000); // 5 requests per 1 second

        IRateLimiterStrategy fixedWindow = new FixedWindowCounterStrategy(config);
        RateLimiterManager manager = new RateLimiterManager(fixedWindow);

        System.out.println("1. Fixed Window Counter (Max 5/sec)");
        simulateTraffic(manager, "user:T1", 8, 0); // Burst 8 immediately

        Thread.sleep(1100); 

        System.out.println("\n--- Switching Strategy ---\n");
        IRateLimiterStrategy slidingWindow = new SlidingWindowCounterStrategy(config);
        manager.setStrategy(slidingWindow);

        System.out.println("2. Sliding Window Counter (Max 5/sec)");
        simulateTraffic(manager, "tenant:A", 3, 0);
        
        Thread.sleep(600); 

        simulateTraffic(manager, "tenant:A", 4, 0);

        System.out.println("\n--- Multi-threading Concurrency Test ---\n");
        runConcurrencyTest(manager, "api_key:XYZ");
    }

    private static void simulateTraffic(RateLimiterManager manager, String key, int requests, int delayMs) throws InterruptedException {
        int allowed = 0;
        int blocked = 0;

        for (int i = 0; i < requests; i++) {
            if (manager.checkAndAllow(key)) {
                allowed++;
                System.out.println("[ALLOWED] External API Called for " + key);
            } else {
                blocked++;
                System.out.println("[DENIED] Rate limit exceeded for " + key);
            }
            if (delayMs > 0) {
                Thread.sleep(delayMs);
            }
        }
        System.out.println("Summary -> Allowed: " + allowed + ", Blocked: " + blocked);
    }

    private static void runConcurrencyTest(RateLimiterManager manager, String key) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger totalAllowed = new AtomicInteger(0);
        AtomicInteger totalBlocked = new AtomicInteger(0);

        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                if (manager.checkAndAllow(key)) {
                    totalAllowed.incrementAndGet();
                } else {
                    totalBlocked.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Concurrency Test Summary (Max 5/sec) -> Allowed: " + totalAllowed.get() + ", Blocked: " + totalBlocked.get());
        if (totalAllowed.get() <= 5) {
            System.out.println("Concurrency test passed! Hard limit respected under thread contention.");
        } else {
            System.out.println("Concurrency test failed! Limit breached.");
        }
    }
}
