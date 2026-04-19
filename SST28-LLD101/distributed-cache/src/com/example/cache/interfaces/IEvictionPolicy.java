package com.example.cache.interfaces;

public interface IEvictionPolicy<K> {
    void keyAccessed(K key);
    K evictKey();
}
