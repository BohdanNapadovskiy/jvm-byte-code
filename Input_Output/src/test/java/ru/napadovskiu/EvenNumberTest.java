package ru.napadovskiu;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/**
 * Test class EvenNumber for check even number in the stream.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 15.11.2016
 */
public class EvenNumberTest {

    /**
     * Method check that entered even number.
     * @throws IOException exception
     */
    @Test
    public void whenInputEvenNumberThenReturnTrue() throws IOException {
        boolean result = false;
        EvenNumber evenNumber = new EvenNumber();

        try (InputStream inputStream = new ByteArrayInputStream("4".getBytes())) {

            result = evenNumber.checkEvenNumber(inputStream);

        }
        catch (IOException error) {
            error.printStackTrace();
        }
        assertThat(result, is(true));
    }

    /**
     * Method check that entered even number.
     * @throws IOException exception
     */
    @Test
    public void whenInputOddNumberThenReturnFalse() {

        boolean result = false;
        EvenNumber evenNumber = new EvenNumber();

        try (InputStream inputStream = new ByteArrayInputStream("3".getBytes())) {

            result = evenNumber.checkEvenNumber(inputStream);

        }
        catch (IOException error) {
            error.printStackTrace();
        }
        assertThat(result, is(false));
    }

 }
