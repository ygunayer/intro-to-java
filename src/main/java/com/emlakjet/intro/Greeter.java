package com.emlakjet.intro;

import com.emlakjet.intro.caching.Cache;
import com.emlakjet.intro.injection.Inject;
import com.emlakjet.intro.injection.Registry;

public class Greeter {
    @Inject
    private Cache<String, String> cache;

    public Greeter() {
        Registry.getInstance().inject(this);
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
