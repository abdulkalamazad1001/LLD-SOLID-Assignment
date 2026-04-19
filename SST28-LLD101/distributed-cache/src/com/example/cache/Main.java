package com.example.cache;

import com.example.cache.models.DistributedCacheService;
import com.example.cache.models.MockDatabaseService;
import com.example.cache.strategies.HashModuloDistributionStrategy;
import com.example.cache.strategies.LRUEvictionPolicy;

public class Main {
    public static void main(String[] args) {
        MockDatabaseService<String, String> db = new MockDatabaseService<>();
        db.saveToDatabase("user:1", "Alice");
        db.saveToDatabase("user:2", "Bob");
        db.saveToDatabase("user:3", "Charlie");

        DistributedCacheService<String, String> cache = new DistributedCacheService<>(
                3, // numNodes
                2, // nodeCapacity
                new HashModuloDistributionStrategy<>(),
                db,
                new LRUEvictionPolicy<>()
        );

        System.out.println("--- Cache Miss Scenarios (Fallthrough to DB) ---");
        System.out.println("Get user:1 -> " + cache.get("user:1"));
        System.out.println("Get user:1 (cached) -> " + cache.get("user:1"));

        System.out.println("\n--- Distribution Test ---");
        cache.put("key1", "val1");
        cache.put("key2", "val2");
        cache.put("key3", "val3");
        cache.put("key4", "val4");
        
        System.out.println("Get key1 -> " + cache.get("key1"));
        System.out.println("Get key2 -> " + cache.get("key2"));
        System.out.println("Get key3 -> " + cache.get("key3"));
        System.out.println("Get key4 -> " + cache.get("key4"));

        System.out.println("\n--- Eviction Test (LRU) ---");
        // key1 and key2 are probably in the same node if hashed similarly, 
        // node capacity is 2. Let's force evictions.
        cache.put("item:A", "Value A");
        cache.put("item:B", "Value B");
        cache.get("item:A"); // Access A to make B least recently used
        cache.put("item:C", "Value C"); // Should evict B in that specific node

        System.out.println("Get item:A -> " + cache.get("item:A"));
        System.out.println("Get item:B (should be DB null) -> " + cache.get("item:B"));
        System.out.println("Get item:C -> " + cache.get("item:C"));
    }
}
