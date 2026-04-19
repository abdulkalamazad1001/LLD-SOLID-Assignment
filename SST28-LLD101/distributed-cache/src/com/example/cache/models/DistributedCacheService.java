package com.example.cache.models;

import com.example.cache.interfaces.ICache;
import com.example.cache.interfaces.IDatabaseService;
import com.example.cache.interfaces.IDistributionStrategy;

import java.util.ArrayList;
import java.util.List;

public class DistributedCacheService<K, V> implements ICache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final IDistributionStrategy<K> distributionStrategy;
    private final IDatabaseService<K, V> databaseService;

    public DistributedCacheService(int numNodes, int nodeCapacity, 
                                 IDistributionStrategy<K> distributionStrategy,
                                 IDatabaseService<K, V> databaseService,
                                 com.example.cache.interfaces.IEvictionPolicy<K> evictionPolicyPrototype) {
        this.nodes = new ArrayList<>();
        this.distributionStrategy = distributionStrategy;
        this.databaseService = databaseService;
        
        for (int i = 0; i < numNodes; i++) {
            // In a real system, we'd clone or create a new factory-based policy
            this.nodes.add(new CacheNode<>(nodeCapacity, new com.example.cache.strategies.LRUEvictionPolicy<>()));
        }
    }

    @Override
    public V get(K key) {
        int nodeIndex = distributionStrategy.identifyNodeIndex(key, nodes.size());
        CacheNode<K, V> node = nodes.get(nodeIndex);
        
        V value = node.get(key);
        if (value == null) {
            value = databaseService.fetchFromDatabase(key);
            if (value != null) {
                node.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        int nodeIndex = distributionStrategy.identifyNodeIndex(key, nodes.size());
        nodes.get(nodeIndex).put(key, value);
    }
}
