package com.emlakjet.intro;

import java.util.HashMap;
import java.util.Map;

public class Cache<T> {
    private Map<String, T> cache = new HashMap<String, T>();

    public T get(String key) {
        return cache.get(key);
    }

    public void set(String key, T value) {
        cache.put(key, value);
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }
}
