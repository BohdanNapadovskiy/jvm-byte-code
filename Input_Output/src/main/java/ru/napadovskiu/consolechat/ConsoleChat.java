package ru.napadovskiu.consolechat;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * Created by program on 07.12.2016.
 */
public class ConsoleChat {

    /**
     * check if exit from chat.
     */
    private boolean isExit = false;

    /**
     * check if chat paused.
     */
    private boolean isPaused = false;

    /**
     * array for random answers.
     */
    private ArrayList<String> list  = new ArrayList<String>();

    /**
    * file for answers.
    */
    private  File fileAnswers;

    /**
    * file for log.
     */
    private  File logFile;

    /**
     * Method create logfile if don't exist.
     * @param file path to the logfile.
     * @throws IOException exception.
     */
    private void createLogFileIfNotExist(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        this.logFile = file;

    }

    /**
     * Method create file with phrases if don't exist.
     * @param file file with phrases.
     * @throws IOException exception.
     */
    private void createPhraseFileIfNotExist(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        this.fileAnswers = file;
    }

    /**
     * Method fill array prases from file.
     * @throws IOException exception.
     */
    private void fillArrayList() throws IOException {
        try (Scanner sc = new Scanner(this.fileAnswers)) {
            while (sc.hasNext()) {
                this.list.add(sc.nextLine());
            }
        }
    }

    /**
     * Method return random string from array for answers.
     * @return result random string from array.
     * @throws IOException exception.
     */
    private String getNextRandomPhrase() throws IOException {
        int linePosition = (int) (Math.random() * list.size());
        String line = this.list.get(linePosition);
        System.out.println(line);
        return line;
    }

    /**
     * Method check status for console chat.
     * @param string string for check.
     */
    private void checkStatusConsoleChat(String string) {
        this.isExit = string.equals("end");
        if (string.equals("stop")) {
            this.isPaused = true;
        } else if (string.equals("continue")) {
            this.isPaused = false;
        }
    }

    /**
     * Method write string to logfile.
     * @param line string for write.
     * @param raf log file.
     * @throws IOException exception.
     */
    private void writeToLogFile(String line, RandomAccessFile raf) throws IOException {
        raf.writeBytes(String.format("%s\r\n", line));

    }

    /**
     * Method check the path for app.properties.
     * @return result string with file path.
     */
    private String returnPathOfProperty() {
        String result = System.getProperty("user.dir");

        String checkString = "Input_Output";

        if (!result.contains(checkString)) {
            result = result + checkString;
        }


        return result;
    }

    /**
     * Main method for start chat.
     * @throws IOException exception.
     */
    public void startConsoleChat() throws IOException {
        Properties props = new Properties();

        props.load(new FileInputStream(new File(returnPathOfProperty() + "\\app.properties")));

        String filePath = props.getProperty("home.path");

        createPhraseFileIfNotExist(new File(filePath + "phraseForConsolechat.txt"));
        createLogFileIfNotExist(new File(filePath + "logFileConsolechat.txt"));

        RandomAccessFile rafLogfile = new RandomAccessFile(this.logFile, "rw");
        fillArrayList();
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isExit &&   scanner.hasNext()) {
                String line = scanner.nextLine();
                checkStatusConsoleChat(line);
                writeToLogFile(line, rafLogfile);
                if (!isPaused && !isExit) {
                    writeToLogFile(getNextRandomPhrase(), rafLogfile);
                    if (!isPaused) {
                        writeToLogFile(line, rafLogfile);
                    }
                }

            }

        }
        rafLogfile.close();
    }

}


