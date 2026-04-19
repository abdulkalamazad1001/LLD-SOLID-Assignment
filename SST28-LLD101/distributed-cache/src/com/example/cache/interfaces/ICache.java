package com.example.cache.interfaces;

public interface ICache<K, V> {
    V get(K key);
    void put(K key, V value);
}
