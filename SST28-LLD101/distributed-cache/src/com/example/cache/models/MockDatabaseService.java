package com.example.cache.models;

import com.example.cache.interfaces.IDatabaseService;

import java.util.HashMap;
import java.util.Map;

public class MockDatabaseService<K, V> implements IDatabaseService<K, V> {
    private final Map<K, V> storage = new HashMap<>();

    public void saveToDatabase(K key, V value) {
        storage.put(key, value);
    }

    @Override
    public V fetchFromDatabase(K key) {
        System.out.println("[DB] Fetching key: " + key);
        return storage.get(key);
    }
}
