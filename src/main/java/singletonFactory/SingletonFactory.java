package singletonFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SingletonFactory {

    private static final SingletonFactory instance = new SingletonFactory();
    private final Map<String, Object> mapHolder = new HashMap<>();

    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> classOf, Object... args) {
        synchronized (SingletonFactory.class) {
            if (!instance.mapHolder.containsKey(classOf.getName())) {
                T obj = null;
                try {
                    Constructor<T> constructor = (Constructor<T>) classOf.getDeclaredConstructors()[0];
                    constructor.setAccessible(true);
                    obj = constructor.newInstance(args);
                } catch (Exception e) {
                    return obj;
                }

                instance.mapHolder.put(classOf.getName(), obj);
            }

            return (T) instance.mapHolder.get(classOf.getName());
        }
    }
}
