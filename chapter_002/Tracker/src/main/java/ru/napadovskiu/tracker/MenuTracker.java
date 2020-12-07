package ru.napadovskiu.tracker;

import ru.napadovskiu.items.Item;
import ru.napadovskiu.items.Comments;

import java.util.ArrayList;
import java.util.List;

/**
 *Class menu tracker.
 */
public class MenuTracker {
    /**
     * input.
     */
    private Input input;

    /**
     *tracker.
     */
    private Tracker tracker;

    /**
     * List for User action.
     */
    private ArrayList<UserAction> actions = new ArrayList<UserAction>();

    /**
     * Constructor with input tracker.
     * @param input input.
     * @param tracker tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     *Method fill action.
     */
    public void fillAction() {
        actions.add(new AddItem());
        actions.add(new ShowAllItem());
        actions.add(new FindItemByName());
        actions.add(new FindItemById());
        actions.add(new EditItem());
        actions.add(new DeleteItem());
        actions.add(new AddCommentsToItem());

   }

    /**
     *Method do action.
      * @param action string with action.
     */
   public void doAction(String action) {
       this.actions.get(Integer.valueOf(action) - 1).execute(this.input, this.tracker);
    }


    /**
     *The method show users menu.
     */
    public void showMenu() {
        System.out.println("1. Add new item ");
        System.out.println("2. Show all item ");
        System.out.println("3. Find item by name ");
        System.out.println("4. Find item by id ");
        System.out.println("5. Edit item ");
        System.out.println("6. Delete item ");
        System.out.println("7. Add comments to item");
        System.out.println("8. Exit");
    }

    /**
     * Method print the question on monitor.
     * @return string with question.
     */
    public String  ask() {
        return this.input.ask("Choice your action");
    }


    /**
     *The method show Item for users.
     *@param arrayItem array item.
     */
    private void showItem(List<Item> arrayItem) {
        for (Item tmpItem : arrayItem) {
            if (tmpItem != null) {
                System.out.println(tmpItem.toString());
            }
        }
    }

    /**
     *The method show Item for users.
     *@param printItem Item for print.
     */

    private void showItem(Item printItem) {
        System.out.println(printItem.toString());
    }

    /**
     *Class add item to tracker.
     */
    private class AddItem implements UserAction {

        /**
        * Method return key.
        * @return key.
        */
        public int key() {

            final int key = 0;
            return key;
        }

        /**
         *Execute method add item.
         * @param input name item.
         * @param tracker Tracker.
         */
        public void execute(Input input, Tracker tracker) {
           Item newItem = new Item(input.ask("please enter the name of item"), input.ask("please enter the description of item"));
           tracker.addNewItem(newItem);

        }

        /**
         * Method show all item.
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }

    }

    /**
     *Class show all item.
     */
    private class ShowAllItem implements UserAction {

        /**
         *Method return key.
         * @return key.
         */
        public int key() {
            final int key = 1;
            return key;
        }

        /**
         **Execute method show all item.
         * @param input input.
         * @param tracker Tracker.
         */
        public void execute(Input input, Tracker tracker) {
            showItem(tracker.getAllItem());

        }

        /**
         * Method show all item.
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all menu");
        }

    }

    /**
     *Class find item by name.
     */
    private class FindItemByName implements UserAction {

        /**
         *Method return key.
         * @return key.
         */
        public int key() {

            final int key = 1;

            return key;
        }

        /**
         *Main method for class Find by name.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            String nameItem = input.ask("please enter the name of item");
            ArrayList<Item> findItem = tracker.findItemByName(nameItem);
            if (findItem.size() != 0) {
                showItem(findItem);
            }

        }

        /**
         *
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by name ");
        }

    }

    /**
     *Class find item by id.
     */
    private class FindItemById implements UserAction {

        /**
         *Method return key.
         *@return key.
         */
        public int key() {

            final int key = 2;

            return key;
        }

        /**
         *Main method for class Find by id.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Item  findItem  = tracker.findItemById(input.ask("please enter id Item"));
            if (findItem != null) {
                showItem(findItem);
            }
        }

        /**
         *
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id");
        }

    }

    /**
     *Class edit item.
     */
    private class EditItem implements UserAction {
        /**
         *Method return key.
         * @return key.
         */
        public int key() {
            final int key = 3;
            return key;
        }

        /**
         *Main method for class edit item.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Item editItem = tracker.findItemById(input.ask("please enter id Item"));
            Item newEditItem = new Item(input.ask("please enter the name of item"), input.ask("please enter the description of item"));
            if (editItem != null) {
                newEditItem.setId(editItem.getId());
                tracker.editItem(newEditItem);
                showItem(editItem);
            }
        }

        /**
         *
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item");
        }

    }

    /**
     *Class delete item.
     */
    private class DeleteItem implements UserAction {

        /**
         *Method return key.
         * @return key.
         */
        public int key() {
            final int key = 4;
            return key;
        }

        /**
         *
         *Main method for class delete item.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Item deleteItem =  tracker.findItemById(input.ask("please enter id Item"));
            if (deleteItem != null) {
                tracker.deleteItem(deleteItem);
                showItem(tracker.getAllItem());
            }

        }

        /**
         *
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }

    }

    /**
    *Class add comments tom item.
    */
    private class AddCommentsToItem implements UserAction {

        /**
         *Method return key.
         * @return key.
         */
        public int key() {
            final int key = 5;
            return key;
        }

        /**
         *Main method for class add comments to item.
         * @param input input.
         * @param tracker tracker.
         */
        public void execute(Input input, Tracker tracker) {
            Comments comment = new Comments(input.ask("please enter comments to item"));
            Item editItem = tracker.findItemById(input.ask("please enter id Item"));
            editItem.addComment(comment);
        }

        /**
         *
         * @return string.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add comments to item");
        }

    }

}