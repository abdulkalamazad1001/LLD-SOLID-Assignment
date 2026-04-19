package com.example.cache.interfaces;

public interface IDatabaseService<K, V> {
    V fetchFromDatabase(K key);
}
