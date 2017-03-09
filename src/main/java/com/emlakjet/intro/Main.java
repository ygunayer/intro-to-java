package com.emlakjet.intro;

import java.nio.file.Paths;

import com.emlakjet.intro.caching.Cache;
import com.emlakjet.intro.caching.FileCache;

public class Main {
    public static void main(String[] args) {
        Cache<String, String> cache = createGreeterCache();

        Greeter greeter = new Greeter(cache);

        System.out.println(greeter.greet("Emlakjet"));
    }

    public static Cache<String, String> createGreeterCache() {
        final String filename = Paths.get(System.getProperty("user.home"), "ej-greet.properties").toString();
        return new FileCache<String, String>(filename);
    }
}
