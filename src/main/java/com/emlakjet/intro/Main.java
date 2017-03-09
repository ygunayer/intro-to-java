package com.emlakjet.intro;

import java.nio.file.Paths;

import com.emlakjet.intro.caching.Cache;
import com.emlakjet.intro.caching.FileCache;
import com.emlakjet.intro.caching.InMemoryCache;
import com.emlakjet.intro.injection.Registry;

public class Main {
    public static void main(String[] args) {
        Registry.getInstance().register(createFileCache());

        Greeter greeter = new Greeter();

        System.out.println(greeter.greet("Emlakjet"));
    }

    public static Cache<String, String> createMemoryCache() {
        return new InMemoryCache<String, String>();
    }

    public static Cache<String, String> createFileCache() {
        final String filename = Paths.get(System.getProperty("user.home"), "ej-greet.properties").toString();
        return new FileCache<String, String>(filename);
    }
}
