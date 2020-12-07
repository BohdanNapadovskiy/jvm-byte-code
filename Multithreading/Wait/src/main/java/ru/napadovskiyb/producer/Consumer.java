package ru.napadovskiyb.producer;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class Consumer implements Runnable {

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
     * @param queue queue for thread.
     * @param maxNumber max number of thread.
     */
    public Consumer(SimpleBlockingQueue queue, int maxNumber) {
        this.blockingQueue = queue;
        this.numberMaxElement = maxNumber;

    }


    /**
     * run method for thread.
     */
    @Override
    public void run() {

        for (int i = 0; i < this.numberMaxElement; i++) {
            System.out.println("get from pool #"  + i);
            this.blockingQueue.poll();
        }

    }
}
