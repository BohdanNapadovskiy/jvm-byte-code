package ru.napadovskiyb.threadpool;

import java.util.Queue;


/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class Worker implements Runnable {


    /**
     * queue of threads.
     */
    private final Queue<Work> threadPoolQueue;

    /**
     * Constructor for class.
     * @param threadPoolQueue queue.
     */
    public Worker(Queue<Work> threadPoolQueue) {
        this.threadPoolQueue = threadPoolQueue;
    }


    /**
     * Method run.
     */
    @Override
    public void run() {
        Work work;
        synchronized (this.threadPoolQueue) {
            while (this.threadPoolQueue.isEmpty()) {
                try {
                    this.threadPoolQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            work = this.threadPoolQueue.poll();
        }

        try {
            work.run();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
