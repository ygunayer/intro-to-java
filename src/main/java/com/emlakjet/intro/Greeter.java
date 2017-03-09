package com.emlakjet.intro;

import java.nio.file.Paths;

import com.emlakjet.intro.caching.Cache;
import com.emlakjet.intro.caching.FileCache;

public class Greeter {
    private Cache<String, String> cache;

    public Greeter() {
        final String filename = Paths.get(System.getProperty("user.home"), "ej-greet.properties").toString();
        cache = new FileCache<String, String>(filename);
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
