package com.example.cache.interfaces;

import java.util.List;

public interface IDistributionStrategy<K> {
    int identifyNodeIndex(K key, int numberOfNodes);
}
