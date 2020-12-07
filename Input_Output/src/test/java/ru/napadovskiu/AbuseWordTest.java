package ru.napadovskiu;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class Class AbuseWord for check abuse word in the string.
 * @author Napadovskiy Bohdan
 * @version 1.00
 * @since 15.11.2016
 */
public class AbuseWordTest {

    /**
     * Method check abuse word in the string when all correct.
     * @throws IOException exception
     */
    @Test
    public void whenAllCorrectThenReturnString() throws IOException {
        String result = null;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("test abuse words with OUT ".getBytes());
        ) {
            System.setOut(new PrintStream(outputStream));

            String[] abuse = new String[]{"test", "Abuse"};

            AbuseWord abuseWord = new AbuseWord();

            abuseWord.dropAbuses(in, outputStream, abuse);
            result = outputStream.toString();
        }
        catch (IOException error) {
            error.printStackTrace();
        }


        assertThat(result, is("  words with OUT "));
    }

}
