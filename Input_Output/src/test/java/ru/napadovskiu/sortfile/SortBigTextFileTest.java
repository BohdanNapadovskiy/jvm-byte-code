package ru.napadovskiu.sortfile;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class SortBigTextFile for sort the source file in the distance file.
 * class implements SortFile interface
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 17.11.2016
 */
public class SortBigTextFileTest {
    /**
     * Method checks result of sort text file.
     * @throws Exception exception
     */
    @Test
    public void whenSortTextFileThenResultSortedFile() throws Exception {
        String[] arrayToSort = new String[] {"999999999", "88888888", "666666", "7777777", "22", "1", "333", "55555", "4444"};

        String[] resultArray = new String[] {"1", "22", "333", "4444", "55555", "666666", "7777777", "88888888", "999999999"};

        final int arrayCount = 9;
        String[] result = new String[arrayCount];

        SortBigTextFile sortBigTextFile = new SortBigTextFile();
        File destination = new File("D:\\distanceText.txt");
        if (!destination.exists()) {
            try {
                destination.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        File fileSource = new File("D:\\sourceText.txt");

        if (!fileSource.exists()) {
            try {
                fileSource.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
        try (RandomAccessFile rafFileSource  = new RandomAccessFile(fileSource.getAbsolutePath(), "rw");
             RandomAccessFile rafFileDestinotion  = new RandomAccessFile(destination.getAbsolutePath(), "rw");
             ) {

                for (String str :arrayToSort) {
                    sortBigTextFile.writeToFile(str, rafFileSource);
                }
                sortBigTextFile.sort(fileSource, destination);

                rafFileDestinotion.seek(0);
                String msg = null;
                int count = 0;
                while ((msg = rafFileDestinotion.readLine()) != null) {
                    result[count] = msg;
                    count++;
                }

            } catch (IOException excep) {
            excep.printStackTrace();
        }

        assertThat(result, is(resultArray));

    }

}