package ru.napadovskiy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Package of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 25.09.2017
 * @param <K> generic keys.
 * @param <V> generic.
 */
public class NonBlockingAlgorithm<K, V extends Model> {

    /**
    *  map for model.
    */
    private ConcurrentHashMap<K, V> nonBlockingMap = new ConcurrentHashMap();


    /**
     * Method add value to map.
     * @param key key of model.
     * @param value value of model.
     */
    public void add(K key, V value) {
        this.nonBlockingMap.put(key, value);
    }

    /**
     * Method delete model from map.
     * @param key key of model.
     */
    public void delete(K key) {
        if (this.nonBlockingMap.containsKey(key)) {
            this.nonBlockingMap.remove(key);
        }
    }

    /**
     * Method update value in map.
     * @param key key of model.
     * @param value value of model.
     */
    public void update(K key, V value) {
        if (this.nonBlockingMap.containsKey(key)) {
            this.nonBlockingMap.computeIfPresent(key, new BiFunction<K, V, V>() {
                @Override
                public V apply(K k, V v) {
                    if (nonBlockingMap.containsKey(key)) {
                        int version = nonBlockingMap.get(key).getVersion();
                        if (v.getVersion() != version) {
                            throw new OptimisticException("Error");
                        } else {
                            int newVersion = v.getVersion();
                            value.setVersion(newVersion++);
                        }
                    }
                    return value;
                }
            });
        }
    }

}
