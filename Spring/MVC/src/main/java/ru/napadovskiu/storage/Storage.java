package ru.napadovskiu.storage;


import java.util.List;

/**
 *
 * @param <T>
 */
public interface Storage<T> {

    /**
     *
     * @param t
     * @return
     */
    boolean update(final T t);

    /**
     *
     * @param t
     * @return
     */
    boolean delete(final T t);

    /**
     *
     * @param id
     * @return
     */
    T get(final int id);


    /**
     *
     * @param name
     * @return
     */
    T getByName(final String name);

    /**
     *
     * @return
     */
    List<T> getAll();

    /**
     *
     * @param t
     * @return
     */
    int add(T t);

    /**
     *
     */
    void close();



}
