package com.emlakjet.intro.caching;

import com.emlakjet.intro.injection.Injectable;

@Injectable
public interface Cache<K, V> {
    boolean contains(K key);

    V get(K key);

    void set(K key, V value);
}
