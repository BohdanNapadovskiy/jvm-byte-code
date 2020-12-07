package ru.napadovskiyb.threadpool;

/**
 * Package of  of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.09.2017
 */
public class Work  implements Runnable {


    /**
     * id thread.
     */
    private final int idThread;

    /**
     * Constructor for class.
     * @param idThread id thread
     */
    public Work(int idThread) {
        this.idThread = idThread;
    }

    /**
     *Method run.
     */
    @Override
    public void run() {
        System.out.println("Run current thread #" + idThread);
    }
}
