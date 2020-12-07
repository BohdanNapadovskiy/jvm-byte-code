package ru.napadovskiy.filesearch;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 06.09.2017
 */
public class SearchFile implements Runnable {

    /**
     * Queue fo file.
     */
    private final ConcurrentLinkedQueue<String> queueFile;

    /**
     * Root dir for search.
     */
    private final String rootDir;

    /**
     * List of extension.
     */
    private final List<String> exts;

    /**
     * Constructor for class.
     * @param queueFile queue file.
     * @param root root dir.
     * @param exts Extension.
     */
    public SearchFile(ConcurrentLinkedQueue<String> queueFile, String root, List<String> exts) {
        this.queueFile = queueFile;
        this.rootDir = root;
        this.exts = exts;
    }


    /**
     * Method return extension.
     * @param fileName file name.
     * @return return extension.
     */
    private static String getFileExtension(String fileName) {
        int index = fileName.indexOf('.');
        return index == -1 ? null : fileName.substring(index);
    }

    /**
     * Method check extension with list of extension.
     * @param fileName file name.
     * @return result.
     */
    private boolean checkExtension(String fileName) {
        boolean result = false;
        if (this.exts.contains(getFileExtension(fileName))) {
            result = true;
        }
        return result;
    }

    /**
     * Method take all files from root dir and check extension.
     * @param root root dir.
     * @param exts extension.
     */
    public void takeAllFilesFromRoot(String root, List<String> exts) {
        synchronized (this) {
            File dir = new File(root);
            File[] arrayOfFile = dir.listFiles();
            if (arrayOfFile != null) {
                for (File item : arrayOfFile) {
                    if (item.isDirectory()) {
                        takeAllFilesFromRoot(item.getPath(), exts);
                    } else {
                        if (checkExtension(item.getPath())) {
                            this.queueFile.offer(item.getPath());
                        }
                    }
                }
            }
        }
        Thread.currentThread().interrupt();
    }

    /**
     * Main method.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            takeAllFilesFromRoot(this.rootDir, this.exts);
        }
    }



}
