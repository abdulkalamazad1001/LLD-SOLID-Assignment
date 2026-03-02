package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry (Proper Singleton).
 * 
 * Refactored to be:
 * 1) Lazy-initialized and thread-safe via Double-Checked Locking.
 * 2) Protected against Reflection attacks.
 * 3) Protected against Serialization attacks.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile MetricsRegistry INSTANCE;
    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        // Protection against Reflection attack
        if (INSTANCE != null) {
            throw new IllegalStateException("MetricsRegistry instance already exists. Use getInstance().");
        }
    }

    public static MetricsRegistry getInstance() {
        // Double-Checked Locking for thread-safety and performance
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Ensures that deserialization returns the same singleton instance.
     */
    protected Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
