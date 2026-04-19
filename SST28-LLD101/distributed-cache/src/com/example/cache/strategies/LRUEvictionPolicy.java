package com.example.cache.strategies;

import com.example.cache.interfaces.IEvictionPolicy;
import com.example.cache.models.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K> {
    private final Map<K, DoublyLinkedListNode<K>> mapper;
    private DoublyLinkedListNode<K> head;
    private DoublyLinkedListNode<K> tail;

    public LRUEvictionPolicy() {
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (mapper.containsKey(key)) {
            detachNode(mapper.get(key));
            insertAtFront(mapper.get(key));
        } else {
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            mapper.put(key, newNode);
            insertAtFront(newNode);
        }
    }

    @Override
    public K evictKey() {
        if (tail == null) {
            return null;
        }
        K keyToEvict = tail.getKey();
        mapper.remove(keyToEvict);
        detachNode(tail);
        return keyToEvict;
    }

    private void insertAtFront(DoublyLinkedListNode<K> node) {
        if (head == null) {
            head = tail = node;
            return;
        }
        node.setNext(head);
        head.setPrev(node);
        head = node;
    }

    private void detachNode(DoublyLinkedListNode<K> node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext();
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            tail = node.getPrev();
        }
        node.setNext(null);
        node.setPrev(null);
    }
}
