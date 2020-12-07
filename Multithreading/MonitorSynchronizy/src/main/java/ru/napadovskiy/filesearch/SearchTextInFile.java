package ru.napadovskiy.filesearch;

import java.io.FileReader;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Package of Multithreading treads.
 * Class counter.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 04.09.2017
 */
public class SearchTextInFile implements Runnable {

    /**
     * Queue with file for search text.
     */
    private final ConcurrentLinkedQueue<String> fileQue;

    /**
     * string for search.
     */
    private final String checkString;

    /**
     * result array.
     */
    private final CopyOnWriteArraySet resultArray;


    /**
     * Constructor for class.
     * @param fileQue Queue with file for search text
     * @param checkString string for search.
     * @param array array for add file.
     */
    public SearchTextInFile(ConcurrentLinkedQueue<String> fileQue, String checkString, CopyOnWriteArraySet array) {
        this.checkString = checkString;
        this.fileQue = fileQue;
        this.resultArray = array;
    }

    /**
     * Method search text in file.
     * @param fileName file for check.
     * @param checkString string for check.
     * @throws IOException exception.
     */
    private void checkTextInFile(String fileName, String checkString) throws IOException {

        FileReader fileReader = new FileReader(fileName);
        Scanner scFile = new Scanner(fileReader);
        while ((scFile.hasNextLine())) {
            String tmpChkString = scFile.nextLine();
            if (tmpChkString.equals(checkString)) {
                this.resultArray.add(fileName);
                break;
            }
        }
        fileReader.close();
    }

    /**
     * Method take file from que and check text.
     * @throws IOException exception.
     */
    public void searchTextInFile() throws IOException {
        while (this.fileQue.iterator().hasNext()) {
            String fileForCheck = this.fileQue.poll();
            checkTextInFile(fileForCheck, this.checkString);
        }
    }

    /**
     *Main method.
     */
    @Override
    public void run()  {
        while (true) {

            if (this.fileQue.isEmpty() && Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                searchTextInFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
