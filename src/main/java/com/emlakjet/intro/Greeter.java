package com.emlakjet.intro;

import com.emlakjet.intro.caching.Cache;

public class Greeter {
    private Cache<String, String> cache;

    public Greeter(Cache<String, String> cache) {
        this.cache = cache;
    }

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
