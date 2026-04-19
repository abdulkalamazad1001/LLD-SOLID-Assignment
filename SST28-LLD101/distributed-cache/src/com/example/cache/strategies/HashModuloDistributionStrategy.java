package com.example.cache.strategies;

import com.example.cache.interfaces.IDistributionStrategy;

public class HashModuloDistributionStrategy<K> implements IDistributionStrategy<K> {
    @Override
    public int identifyNodeIndex(K key, int numberOfNodes) {
        return Math.abs(key.hashCode()) % numberOfNodes;
    }
}
