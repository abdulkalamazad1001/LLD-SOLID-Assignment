package com.example.cache.models;

import com.example.cache.interfaces.ICache;
import com.example.cache.interfaces.IEvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> implements ICache<K, V> {
    private final int capacity;
    private final Map<K, V> storage;
    private final IEvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, IEvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public V get(K key) {
        if (!storage.containsKey(key)) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    @Override
    public void put(K key, V value) {
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (storage.size() >= capacity) {
            K keyToEvict = evictionPolicy.evictKey();
            if (keyToEvict != null) {
                storage.remove(keyToEvict);
            }
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}
