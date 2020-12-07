package ru.napadovskiu;

import org.junit.Test;

import java.io.IOException;
import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class Class Polidrome for check word is polidrome.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 30.11.2016
 */
public class PolidromeTest {

    /**
     * Method check word is poldrome when all correct.
     * @throws IOException exception
     */
    @Test
    public void whenAllCorrectThenReturnTrue() throws IOException {
        boolean isPolidrome = false;
        boolean checkResult = true;
        try (ByteArrayInputStream in = new ByteArrayInputStream("РоТор".getBytes());) {
            Polidrome polidrome = new Polidrome();
            polidrome.createCheckString(in);
            isPolidrome = polidrome.checkWordIsPolidrome();

        } catch (IOException exception) {
            exception.printStackTrace();

        }
        assertThat(isPolidrome, is(checkResult));
    }

    /**
     * Method check length of string.
     * @throws IOException exception
     */
    @Test
    public void whenLenghtUncorrectThenReturnFalse() throws IOException {
        boolean isPolidrome = false;
        boolean checkResult = false;
        try (ByteArrayInputStream in = new ByteArrayInputStream("РоТ".getBytes());) {
            Polidrome polidrome = new Polidrome();

            polidrome.createCheckString(in);

            isPolidrome = polidrome.checkLengthWord();

        } catch (IOException exception) {
            exception.printStackTrace();

        }
        assertThat(isPolidrome, is(checkResult));

    }

    /**
     * * Method check word is poldrome when all correct.
     * @throws IOException exception
     */
    @Test
    public void whenWordIsUncorrectTheReturnFalse() throws IOException {
        boolean isPolidrome = false;
        boolean checkResult = false;
        try (ByteArrayInputStream in = new ByteArrayInputStream("РоТap".getBytes());) {
            Polidrome polidrome = new Polidrome();

            polidrome.createCheckString(in);

            isPolidrome = polidrome.checkWordIsPolidrome();

        } catch (IOException exception) {
            exception.printStackTrace();

        }
        assertThat(isPolidrome, is(checkResult));


    }

}
