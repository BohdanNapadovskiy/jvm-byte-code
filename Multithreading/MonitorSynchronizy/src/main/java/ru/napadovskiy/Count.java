package ru.napadovskiy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Package of Multithreading treads.
 * Class counter.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 04.09.2017
 */

@ThreadSafe
public class Count {


    /**
     * counter.
     */
    @GuardedBy("this")
    private int counter;

    /**
     * Method to increment counter.
     */
    public void increment() {
        synchronized (this) {
            this.counter++;
        }
    }

    /**
     * Method return counter.
     * @return counter.
     */
    public int getCounter() {
        synchronized (this) {
            return  this.counter;
        }
    }

}
