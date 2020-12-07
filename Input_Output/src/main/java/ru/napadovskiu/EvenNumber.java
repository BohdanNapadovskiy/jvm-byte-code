package ru.napadovskiu;

import java.io.IOException;
import java.io.InputStream;
/**
 * Class EvenNumber for check even number in the stream.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 15.11.2016
 */
public class EvenNumber {

    /**
     * Method for check even number in the stream.
     * @param inputStream input stream for cheek
     * @return result
     * @throws IOException exception
     */
    public boolean checkEvenNumber(InputStream inputStream) throws IOException {
        boolean result = false;
        if (inputStream.read() % 2 == 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

}
