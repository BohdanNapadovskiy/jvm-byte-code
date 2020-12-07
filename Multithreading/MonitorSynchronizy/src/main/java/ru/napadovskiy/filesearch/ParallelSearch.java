package ru.napadovskiy.filesearch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Package of Multithreading treads.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 04.09.2017
 */
public class ParallelSearch {

    /**
     * root dir.
     */
    private final String root;

    /**
     * text for search.
     */
    private final String text;

    /**
     * list exts.
     */
    private final List<String> exts;


    /**
     * Constructor for class.
     * @param root root dir
     * @param text text for search.
     * @param exts exts.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }


    /**
     * Method search file and text in files.
     * @throws InterruptedException exception.
     */
    public void searchTextInFiles() throws InterruptedException {
        CopyOnWriteArraySet<String> resultArraySet = new CopyOnWriteArraySet<>();
        ConcurrentLinkedQueue<String> queueOfFile = new ConcurrentLinkedQueue<>();

        SearchFile searchFile = new SearchFile(queueOfFile, this.root, this.exts);
        SearchTextInFile searchTextInFile = new SearchTextInFile(queueOfFile, this.text, resultArraySet);

        Thread searchFileThread = new Thread(searchFile);

        Thread searchTextInFileThread = new Thread(searchTextInFile);

        searchFileThread.start();

        searchTextInFileThread.start();

        searchFileThread.join();

        searchTextInFileThread.interrupt();

        System.out.println(resultArraySet);

    }


    /**
     * Main method.
     * @param args array of strings.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        String rootDir  = "D:\\projects";
        String text     = "Bye-bye!";
        List<String> exts = new ArrayList<>();
        exts.add(".txt");
        exts.add(".csv");
        ParallelSearch parallelSearch = new ParallelSearch(rootDir, text, exts);
        parallelSearch.searchTextInFiles();

    }

}
