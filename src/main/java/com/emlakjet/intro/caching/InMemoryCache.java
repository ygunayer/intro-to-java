package com.emlakjet.intro.caching;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCache<K, V> implements Cache<K, V> {
    private Map<K, V> store = new HashMap<K, V>();

    @Override
    public boolean contains(K key) {
        return store.containsKey(key);
    }

    @Override
    public V get(K key) {
        return store.get(key);
    }

    @Override
    public void set(K key, V value) {
        store.put(key, value);
    }
}
