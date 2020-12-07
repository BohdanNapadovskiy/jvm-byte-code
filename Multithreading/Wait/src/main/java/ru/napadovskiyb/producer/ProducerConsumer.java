package ru.napadovskiyb.producer;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class ProducerConsumer {

    /**
     * Main method.
     * @param args array strings.
     */
    public static void main(String[] args) {
        SimpleBlockingQueue blockingQueue  = new SimpleBlockingQueue();
        final int maxElement = 5;
        Thread consumerThread = new Thread(new Consumer(blockingQueue, maxElement));
        Thread producerThread = new Thread(new Producer(blockingQueue, maxElement));

        Thread one = new Thread(producerThread);
        Thread two = new Thread(consumerThread);

        two.start();
        one.start();

        try {
            one.join();
            two.join();
            one.interrupt();
            two.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
