package com.emlakjet.intro;

public class Greeter {
    private Cache<String> cache = new Cache<String>();

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
