package ru.napadovskiyb.lock;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class SimpleLock {

    /**
     * flag for check lock.
     */
    private boolean isLocked = false;

    /**
     * Method for lock.
     * @throws InterruptedException exception.
     */
    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    /**
     * Method for unlock.
     */
    public synchronized void unlock() {
        isLocked = false;
        notifyAll();
    }

}
