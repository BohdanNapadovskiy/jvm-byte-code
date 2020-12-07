package ru.napadovskiu.sortfile;

import java.io.IOException;
import java.io.File;

/**
 * Interface SortBigTextFile for sort the source file in the distance file.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 17.11.2016
 */
public interface SortFile {
    /**
     * Interface for sort source file to the distance file.
     * @param source source file for the sort
     * @param distance distance file
     * @throws IOException exception
     */
    void sort(File source, File distance) throws IOException;


}
