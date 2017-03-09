package com.emlakjet.intro.injection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {
    private static Registry instance;

    static {
        instance = new Registry();
    }

    private Map<Class<?>, Object> registry = new HashMap<Class<?>, Object>();

    private Registry() {
    }

    public void inject(Object callee) {
        Field[] fields = callee.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }

            Class<?> type = field.getType();
            Object injectable = registry.get(type);

            field.setAccessible(true);
            try {
                field.set(callee, injectable);
            } catch (IllegalArgumentException e) {
                e.printStackTrace(); // pls no
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // pls no
            }
        }
    }

    public void register(Object o) {
        List<Class<?>> interfaces = Arrays.asList(o.getClass().getInterfaces());

        interfaces.stream().filter(i -> i.isAnnotationPresent(Injectable.class)).findFirst()
                .ifPresent(type -> registry.put(type, o));
    }

    public static Registry getInstance() {
        return instance;
    }

}
