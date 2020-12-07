package ru.napadovskiu.consolechat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.Before;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;


/**
 * Created by program on 19.12.2016.
 */
public class ConsoleChatTest {
    /**
     * Output stream for tests.
     */
    private ByteArrayOutputStream out;

    /**
     * Method prepare output stream before tests.
     */
    @Before
    public void prepareOutputStream() {
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Method checks that after random entered word program print random message.
     * @throws IOException exception.
     */
    @Test
    public void whenAllCorrectThenReturnTrue() throws IOException {
        String stopKey = String.format("test%stest2", System.getProperty("line.separator"), System.getProperty("line.separator"));
        System.setIn(new ByteArrayInputStream(stopKey.getBytes()));

        ConsoleChat myChat = new ConsoleChat();


        try {
            myChat.startConsoleChat();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.out.toString().isEmpty(), is(false));
    }

    /**
     * Method checks that after word stop there is no messages.
     */
    @Test
    public void whenEnterStopAndOtherWordsThenResultIsEmpty() {
        String stopKey = String.format("stop%stest%stest2", System.getProperty("line.separator"), System.getProperty("line.separator"));
        System.setIn(new ByteArrayInputStream(stopKey.getBytes()));
        ConsoleChat myChat = new ConsoleChat();
        try {
            myChat.startConsoleChat();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.out.toString().isEmpty(), is(true));
    }

    /**
     * Method checks that after word stop and continue there are messages form program.
     */
    @Test
    public void whenEnterStopAndContinueResultRandomMessage() {
        String stopAndContinueKey = String.format("stop%scontinue%stest", System.getProperty("line.separator"), System.getProperty("line.separator"));
        System.setIn(new ByteArrayInputStream(stopAndContinueKey.getBytes()));
        ConsoleChat myChat = new ConsoleChat();
        try {
            myChat.startConsoleChat();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        assertThat(this.out.toString().isEmpty(), is(false));
    }




}
