package ru.napadovskiub;

/**
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 17.07.2017
 * @param <T> generic.
 * @param <V> generic.
 */
public interface MyMap<T, V> {

    /**
     * Method add element to collection.
     * @param key key element.
     * @param value value element.
     * @return result.
     */
    boolean insert(T key, V value);

    /**
     * Method return value bu key.
     * @param key for search.
     * @return value element.
     */
    V get(T key);

    /**
     * Method delete element by key.
     * @param key key for search.
     * @return result.
     */
    boolean delete(T key);

}
