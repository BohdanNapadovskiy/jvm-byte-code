package ru.napadovskiub.store;


/**
 * Class for Role storage.
 * @param <T> generic.
 */
public class RoleStore<T extends Role> extends SimpleArrayStore {

    /**
     * Constructor class with size.
     * @param size array size.
     */
    public RoleStore(int size) {
        super(size);
    }
}
