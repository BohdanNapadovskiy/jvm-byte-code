package ru.napadovskiub.exception;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 15.06.2017
 */
public class InsufficientFundsException extends Exception {

    /**
     * Method return message with error.
     * @param msg message with error.
     */
    public InsufficientFundsException(String msg) {
        super(msg);
    }

}
