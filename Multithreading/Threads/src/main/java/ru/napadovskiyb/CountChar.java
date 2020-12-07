package ru.napadovskiyb;


/**
 * Package of Multithreading finalTask.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 29.08.2017
 */
public class CountChar implements Runnable {

    /**
     * String for check.
     */
    private String stringForCheck;

    /**
     *Constructor for class.
     * @param stringForCheck string.
     */
    public CountChar(String stringForCheck) {
        this.stringForCheck = stringForCheck;
    }

    /**
     *Method run.
     */
    @Override
    public void run() {
        int count = 0;
        for (char element : this.stringForCheck.toCharArray()) {
            count++;
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Поток был принудительно завершен число символов: " + count);
                break;
            }
        }
        System.out.println("Поток был завершен коректно число символов: " + count);
    }
}

