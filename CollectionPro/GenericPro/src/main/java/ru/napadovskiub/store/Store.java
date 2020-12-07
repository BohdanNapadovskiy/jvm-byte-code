package ru.napadovskiub.store;

/**
 *Interface for implements.
 * @param <T> generic.
 */
public interface Store<T extends Base> {

    /**
     *Method add value to storage.
     *@param value add value.
     */
    void add(T value);

    /**
     *Method update value in array.
     * @param id position for update.
     * @param newValue new value for update.
     */
    void update(String id, T newValue);

    /**
     *Method delete value from array.
     * @param t value for delete.
     */
    void delete(T t);

}
