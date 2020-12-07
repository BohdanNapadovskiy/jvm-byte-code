package ru.napadovskiu.tracker;


/**
 *Class for users.
 */
public class StartUI {
    /**
     *Input.
     */
    private Input input;

    /**
     * Tracker for item.
     */
    private Tracker tracker;


    /**
     *
     * @param input input.
     * @param tracker tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     *Main method for users.
     */
    public void init() {
        boolean exit = false;
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        menuTracker.showMenu();
        final int keyExit = 8;
        do {
            String choiseAction = menuTracker.ask();
            if (Integer.valueOf(choiseAction) == keyExit) {
                exit = true;
            } else {
                menuTracker.fillAction();
                menuTracker.doAction(choiseAction);
            }
        }
        while (!exit);
    }

    /**
     *Main method.
     * @param args args.
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input, tracker);
        startUI.init();
    }

}

