package ru.napadovskiu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Class Polidrome check that word is polidrome.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 30.11.2016
 */
public class Polidrome {

    /**
     *  string for checking.
     */
    private String checkString;

    /**
     * string length.
     */
    private final int maxLength = 5;

    /**
     * Method create string from input stream.
     * @param inputStream input stream
     */
    public void createCheckString(InputStream inputStream) {
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            while ((ch = inputStreamReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            this.checkString = stringBuilder.toString();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method check word is polidrome.
     * @return result
     */
    public boolean checkWordIsPolidrome() {
        boolean result = false;
        if (checkLengthWord()) {
            StringBuilder reversWord = new StringBuilder();
            for (int i = this.checkString.length() - 1; i >= 0; i--) {
                reversWord.append(this.checkString.charAt(i));
            }
            if (this.checkString.toLowerCase().equals(reversWord.toString().toLowerCase())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * method checks length of string for checking.
     * @return result
     */
    public boolean checkLengthWord() {
        boolean result = false;
        if (this.checkString != null) {
            if (this.checkString.length() == maxLength) {
                result = true;
            }
        }
        return result;
    }
}
