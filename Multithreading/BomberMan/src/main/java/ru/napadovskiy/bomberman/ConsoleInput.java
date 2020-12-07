package ru.napadovskiy.bomberman;

import java.util.Scanner;

/**
 * Package of Multithreading test task.
 * Class console input.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 23.11.2017

 */
public class ConsoleInput {

    /**
     *
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param question
     * @return
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     *
     * @param question
     * @return
     */
    public String askUser(String question) {
        String text = "";
        while (text.equals("w") & text.equals("s") & text.equals("a") & text.equals("d")) {
            text = ask(question);
        }
        return text;
    }
}