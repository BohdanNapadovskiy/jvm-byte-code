package ru.napadovskiu.tracker;

/**
 *
 */
public class StubInput implements  Input {

    /**
     *User answers.
     */
    private String[] answers;

    /**
     * position.
     */
    private  int position = 0;

    /**
     *
     * @param answers answers.
     */
    public  StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Method ask user.
     * @param question question.
     * @return position.
     */
    public String ask(String question) {
        return answers[this.position++];
    }

}
