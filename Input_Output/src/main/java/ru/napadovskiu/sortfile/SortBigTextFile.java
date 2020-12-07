package ru.napadovskiu.sortfile;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Class SortBigTextFile for sort the source file in the distance file.
 * class implements SortFile interface
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 17.11.2016
 */

public class SortBigTextFile implements SortFile {
    /**
     * directory for temporary files.
     */
    private static File dirfortempfile = new File("D:\\tempforTest");

    /**
     * Arrays of temporary files.
     */
    private ArrayList<File> list  = new ArrayList<File>();

    /**
     * source file.
     */
    private RandomAccessFile fileSorce;

    /**
     * distance file.
     */
    private RandomAccessFile fileDistance;

    /**
     * Method create temporary directory.
     * @throws IOException exception
     */
    private void createDirectory() throws IOException {
        if (!dirfortempfile.exists()) {
            dirfortempfile.mkdir();
        }
    }

    /**
     * Method sort strings from file source to the file distance.
     * @param source source file
     * @param distance distance file
     * @throws IOException exception
     */
    public void sort(File source, File distance) throws IOException {
        initFiles(source, distance);
        splitSourceFile();
        mergeToDistanceFile();

    }

    /**
     * Method write string to the file.
     * @param line string for write in the file
     * @param raf file
     * @throws IOException exception
     */
    public void writeToFile(String line, RandomAccessFile raf) throws IOException {
        raf.writeBytes(String.format("%s\r\n", line));
    }

    /**
     * Method create object RandomAccessFile.
     * @param source source file
     * @param distance distance file
     */
    private void initFiles(File source, File distance) {

       try {
           createDirectory();
       } catch (IOException exception) {
           exception.printStackTrace();
       }

       try {
           this.fileSorce = new RandomAccessFile(source.getAbsolutePath(), "r");
           this.fileDistance = new RandomAccessFile(distance.getAbsolutePath(), "rw");
       } catch (FileNotFoundException exception) {
           exception.printStackTrace();
       }

    }

    /**
     * Method create temporary file.
     * @return File tmpFile
     * @throws IOException exception
     */
    private File createTmpFile() throws IOException {
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("tmpFile", ".txt", dirfortempfile);

        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return tmpFile;
    }

    /**
     * Method sorts file.
     * @param tmp temporary files for sort
     * @throws IOException exception
     */
    private void sortTmpFile(RandomAccessFile tmp) throws IOException {
        String msg = null;
        ArrayList<String> strList  = new ArrayList<String>();

        tmp.seek(0);
        while ((msg = tmp.readLine()) != null) {
            strList.add(msg);
        }
        for (int i = strList.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (strList.get(j).length() > strList.get(j + 1).length()) {
                    String t = strList.get(j);
                    strList.set(j, strList.get(j + 1));
                    strList.set(j + 1, t);
                }
            }
            tmp.seek(0);
            for (String tmpMsg : strList) {
                writeToFile(tmpMsg, tmp);
            }
        }
        tmp.close();
    }

    /**
     * Method split source file.
     * @throws IOException exception
     */
    private void splitSourceFile() throws  IOException {
        String msg = null;
        ArrayList<RandomAccessFile>  rafList = new ArrayList<RandomAccessFile>();
        int countFile = 2;
        int count = 0;
        for (int i = 0; i < countFile; ++i) {
            File tmpFile = createTmpFile();
            tmpFile.deleteOnExit();
            list.add(tmpFile);
            rafList.add(new RandomAccessFile(tmpFile, "rw"));
        }

        while ((msg = this.fileSorce.readLine()) != null) {
            writeToFile(msg, rafList.get(count));
            if (count == 1) {
                count = 0;
            } else {
                count++;
            }

        }
        for (RandomAccessFile fileFromList : rafList) {
            sortTmpFile(fileFromList);
        }

    }

    /**
     * Method merge temp files to the distance file.
     * @throws IOException exception
     */
    private void mergeToDistanceFile() throws IOException {

        boolean unsortedFile = true;

        String firstMsg = null;
        String secondMsg = null;
        int i = 0, j = 1;

        for (; j < list.size();) {
            File tmpFirstFile  =  list.get(i);
            tmpFirstFile.deleteOnExit();
            File tmpSecondFile = list.get(j);
            tmpSecondFile.deleteOnExit();
            RandomAccessFile tempFileFirst  = new RandomAccessFile(list.get(i), "rw");
            RandomAccessFile tempFileSecond = new RandomAccessFile(list.get(j), "rw");
            tempFileFirst.seek(0);
            tempFileSecond.seek(0);

            while (unsortedFile) {
                if (firstMsg == null) {
                    firstMsg = tempFileFirst.readLine();
                }
                if (secondMsg == null) {
                    secondMsg = tempFileSecond.readLine();
                }
                if (firstMsg == null && secondMsg != null) {
                    this.writeToFile(secondMsg, fileDistance);
                    secondMsg = null;
                } else if (firstMsg != null && secondMsg == null) {
                    this.writeToFile(firstMsg, fileDistance);
                    firstMsg = null;
                }
                if (firstMsg != null && secondMsg != null) {
                    if (firstMsg.length() <= secondMsg.length()) {
                        this.writeToFile(firstMsg, fileDistance);
                        firstMsg = null;
                    } else {
                        this.writeToFile(secondMsg, fileDistance);
                        secondMsg = null;
                    }
                }
                if (firstMsg == null && secondMsg == null) {
                    unsortedFile = false;
                }

            }
            unsortedFile = true;
            i = j + 1;
            j = i + 1;

            tempFileFirst.close();
            tempFileSecond.close();
            tmpFirstFile.deleteOnExit();
            tmpSecondFile.deleteOnExit();
        }

    }

}
