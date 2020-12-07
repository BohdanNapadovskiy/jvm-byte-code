package ru.napadovskiyb;


import java.util.StringTokenizer;

/**
 * Package of CollectionPro finalTask.
 * Main class for order book.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 09.08.2017
 */
public class CheckString  {

    /**
     * Method calculate space in string.
     * @param stringForCheck string.
     * @return result.
     */
    public Thread calcSpace(String  stringForCheck) {
        return new Thread() {
            @Override
            public void run() {
                if (isInterrupted()) {
                    interrupted();
                    return;
                }
                char checkChar = ' ';
                int result = 0;
                char[] charsString = stringForCheck.toCharArray();
                for (char tmpChar : charsString) {
                    if (tmpChar == checkChar) {
                        result++;
                    }
                }
                System.out.println(result);
            }
        };

    }


    /**
     *  Method calculate words in string.
     * @param stringForCheck string for check.
     * @return result
     */
    public Thread calcWorlds(String  stringForCheck) {
        return new Thread() {
            @Override
            public void run() {
                int count = 0;
                if (isInterrupted()) {
                    interrupted();
                    return;
                }

                StringTokenizer stringTokenizer = new StringTokenizer(stringForCheck);

                while (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextElement();
                    count++;

                }
                System.out.println(count);
            }
        };
    }

    /**
     * Main thread.
     * @return thread.
     */
    public Thread mainThread() {
        return new Thread() {

            private String  newString = "а баба галамага тест";

            private final int timeStop = 1000;

            @Override
            public void run() {

                Thread firstThread = calcSpace(newString);
                Thread secondThread = calcWorlds(newString);

                try {
                    firstThread.start();
                    secondThread.start();

                    firstThread.join(timeStop);
                    secondThread.join(timeStop);

                    firstThread.interrupt();
                    secondThread.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Main method.
     * @param args array string.
     */
    public static void main(String[] args) {
        System.out.println("Start");
        CheckString checkString = new CheckString();
        Thread mainThread = checkString.mainThread();
        mainThread.start();

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainThread.interrupt();
        System.out.println("Finish");

    }

}



