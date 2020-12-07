package ru.napadovskiy.racecondition;

/**
 * Package of Multithreading.
 * Main class for test.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 31.08.2017
 */
public class TestRaceCondition {

    /**
     * Main method for check.
     * @param args array string.
     */
    public static void main(String[] args) {
        RaceCondition raceCondition = new RaceCondition();
        Thread firstThread = new Thread(raceCondition);
        Thread secondThread = new Thread(raceCondition);

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        firstThread.interrupt();
        secondThread.interrupt();

        System.out.println("All thread finish " + raceCondition.getCount());

    }
}
