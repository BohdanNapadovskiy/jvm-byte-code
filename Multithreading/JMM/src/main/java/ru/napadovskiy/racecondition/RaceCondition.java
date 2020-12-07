package ru.napadovskiy.racecondition;

/**
 * Package of Multithreading.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 31.08.2017
 */
public class RaceCondition implements Runnable {

    /**
     * Counter.
     */
    private int count;

    /**
     * Method add counter.
     * @param amount amount.
     */
    public void addToCount(int amount) {
        this.count += amount;

    }

    /**
     * Method return counter.
     * @return count.
     */
    public int getCount() {
        return count;
    }


    /**
     * Method return.
     */
    @Override
    public void run() {

        final int checkCount = 2000;
        final int maxI = 10;

        while (!Thread.currentThread().isInterrupted() && this.count < checkCount) {
            for (int i = 0; i < maxI; i++) {
                this.addToCount((1));
            }
            try {
                Thread.currentThread().sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
