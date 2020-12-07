package ru.napadovskiub.store;

/**
 * Class storage user.
 * @param <T> generic.
 */
public class UserStore<T extends User> extends SimpleArrayStore {

    /**
     *Constructor with size.
     * @param size array size.
     */
    public UserStore(int size) {
        super(size);
    }
}
