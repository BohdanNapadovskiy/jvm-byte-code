package ru.napadovskiy.threadsafestorage;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <E> generic.
 * @since 08.07.2017
 */
public interface SimpleContainer<E> extends Iterable<E> {

    /**
     *Method add element too list.
     * @param e value
     */
    void add(E e);

    /**
     *Method add return value from list.
     * @param index index.
     * @return value.
     */
    E get(int index);
}
