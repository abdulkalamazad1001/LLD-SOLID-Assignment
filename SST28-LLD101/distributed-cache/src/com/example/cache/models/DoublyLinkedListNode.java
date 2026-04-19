package com.example.cache.models;

public class DoublyLinkedListNode<K> {
    private final K key;
    private DoublyLinkedListNode<K> next;
    private DoublyLinkedListNode<K> prev;

    public DoublyLinkedListNode(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public DoublyLinkedListNode<K> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode<K> next) {
        this.next = next;
    }

    public DoublyLinkedListNode<K> getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedListNode<K> prev) {
        this.prev = prev;
    }
}
