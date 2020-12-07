package ru.napadovskiu.tracker;


import java.util.Scanner;

/**
 * Class from work with console.
 */
public class ConsoleInput implements Input {

    /**
     * Scanner for read text from console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     *Method generate question.
     * @param question question for user.
     * @return question.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
     }

}

