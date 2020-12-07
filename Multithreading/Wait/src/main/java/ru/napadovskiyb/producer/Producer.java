package ru.napadovskiyb.producer;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class Producer implements Runnable {

    /**
     * queue for thread.
     */
    private final SimpleBlockingQueue blockingQueue;

    /**
     * max count for thread.
     */
    private final int numberMaxElement;

    /**
     * Constructor for consumer class.
     * @param blockingQueue queue for thread.
     * @param maxNumber max number of thread.
     */
    public Producer(SimpleBlockingQueue blockingQueue, int maxNumber) {
        this.blockingQueue = blockingQueue;
        this.numberMaxElement = maxNumber;
    }

    /**
     * run method for thread.
     */
    @Override
    public void run() {
        for (int i = 0; i <  this.numberMaxElement; i++) {
            this.blockingQueue.add(i);
            System.out.println("Add to pool #" + i);
        }
    }
}
