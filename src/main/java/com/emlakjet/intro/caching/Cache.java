package com.emlakjet.intro.caching;

public interface Cache<K, V> {
    boolean contains(K key);

    V get(K key);

    void set(K key, V value);
}
