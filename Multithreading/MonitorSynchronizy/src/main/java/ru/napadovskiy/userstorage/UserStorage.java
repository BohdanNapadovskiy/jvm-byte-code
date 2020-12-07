package ru.napadovskiy.userstorage;

import net.jcip.annotations.ThreadSafe;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Package of Multithreading treads.
 * Class counter.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 04.09.2017
 */
@ThreadSafe
public class UserStorage {

    /**
     * User storage.
     */
    private ConcurrentHashMap<Integer, User> userStore = new ConcurrentHashMap<>();

    /**
     * size storage.
     */
    private int sizeStorage;

    /**
     *Method return get suze storage.
     * @return size.
     */
    public int getSizeStorage() {
        return this.sizeStorage;
    }

    /**
     * Method add user to storage.
     * @param user user.
     * @return result.
     */
    public boolean add(User user) {
        synchronized (this) {
            boolean result = false;
            if (!this.userStore.containsKey(user.getIdUser())) {
                this.userStore.put(user.getIdUser(), user);
                this.sizeStorage++;
                result = true;

            }
            return result;
        }
    }


    /**
     * Method delete user from storage.
     * @param user user.
     * @return result.
     */
    public boolean delete(User user) {
        synchronized (this) {
            boolean result = false;
            if (this.userStore.containsKey(user.getIdUser())) {
                this.userStore.remove(user.getIdUser(), user);
                result = true;
                this.sizeStorage--;
            }
            return result;
        }
    }

    /**
     * Method update user.
     * @param user user.
     * @param amount amount.
     */
    public void update(User user, int amount) {
        synchronized (this) {
            if (this.userStore.containsKey(user.getIdUser())) {
                User editUser = this.userStore.get(user.getIdUser());
                editUser.setAmount(amount);
            }
        }
    }

    /**
     * Method get user from storage for id.
     * @param userId user id.
     * @return result.
     */
    public User getById(int userId) {
        User result = null;
        if (this.userStore.containsKey(userId)) {
            result = this.userStore.get(userId);
        }
        return result;
    }

    /**
     * Method transfer money from user 1 to user 2.
     * @param fromId id user 1.
     * @param toId id user 2.
     * @param amount amount.
     */
    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            User fromUser  = this.userStore.get(fromId);
            User toUser  = this.userStore.get(toId);
            if ((!fromUser.equals(null)) && (!toUser.equals(null))) {
                if (fromUser.getAmount() >= amount) {
                     this.update(fromUser, fromUser.getAmount() - amount);
                     this.update(toUser, fromUser.getAmount() + amount);
                }
            }
        }
    }

}
