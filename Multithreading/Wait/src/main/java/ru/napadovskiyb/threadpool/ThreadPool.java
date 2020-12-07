package ru.napadovskiyb.threadpool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class ThreadPool {


    /**
     * Queue for thread.
     */
    private final Queue<Work> threadPoolQueue;

    /**
     * var for size queue.
     */
    private static final int CPU_NUMBER = Runtime.getRuntime().availableProcessors();

    /**
     * var for check status thread.
     */
    private volatile boolean isRunning = true;

    /**
     * Constructor for class.
     * @param testQueue queue.
     */
    public ThreadPool(Queue<Work> testQueue) {
        threadPoolQueue = testQueue;
        for (int i = 0; i < CPU_NUMBER; i++) {
            new Thread(new Worker(this.threadPoolQueue)).start();
            add(new Work(i));
        }
    }

    /**
     * Method add thread to queue.
     * @param work thread work.
     */
    public synchronized void add(Work work) {
        synchronized (this.threadPoolQueue) {
            this.threadPoolQueue.offer(work);
            this.threadPoolQueue.notify();
        }
    }


    /**
     * Method shutdown ThreadPool.
     */
    public synchronized  void shutDown() {
        this.isRunning = false;
    }

    /**
     * Main method.
     * @param args string of args.
     */
    public static void main(String[] args) {
        Queue<Work> testQueue = new LinkedList<>();
        ThreadPool threadPool = new ThreadPool(testQueue);
        threadPool.shutDown();
   }
}
