package ru.napadovskiy;

/**
 * Package of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 25.09.2017
 */
public class OptimisticException extends RuntimeException {

    /**
     * Method return error message.
     * @param error message.
     */
    public OptimisticException(String error) {
        super(error);
    }
}
