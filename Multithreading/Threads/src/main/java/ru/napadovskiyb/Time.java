package ru.napadovskiyb;

/**
 * Package of CollectionPro finalTask.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 09.08.2017
 */
public class Time implements Runnable {

    /**
     * Time limit.
     */
    private long timeLimit;

    /** thread for count char.
     *
     */
    private Thread countCharThread;


    /**
     * Constructor for class.
     * @param timeLimit time limit.
     * @param countCharThread count char.
     */
    public Time(long timeLimit, Thread countCharThread) {
        this.timeLimit = timeLimit;
        this.countCharThread = countCharThread;
    }


    /**
     *Method run.
     */
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        long finishTime = 0;
        this.countCharThread.start();

        while (!Thread.currentThread().isInterrupted()) {
            finishTime = System.currentTimeMillis() - startTime;
            if (finishTime >= this.timeLimit) {
                this.countCharThread.interrupt();
                Thread.currentThread().interrupt();
                break;

            }
        }

    }
}
