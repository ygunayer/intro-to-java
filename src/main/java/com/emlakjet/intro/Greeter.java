package com.emlakjet.intro;

import com.emlakjet.intro.caching.Cache;
import com.emlakjet.intro.caching.InMemoryCache;

public class Greeter {
    private Cache<String, String> cache = new InMemoryCache<String, String>();

    public String greet(String who) {
        if (cache.contains(who)) {
            return cache.get(who);
        }

        System.out.println("# Generating for " + who);

        String greeting = "Hello, " + who + "!";
        cache.set(who, greeting);

        return greeting;
    }
}
