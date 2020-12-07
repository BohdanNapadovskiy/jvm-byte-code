package ru.napadovskiyb;

/**
 * Package of CollectionPro dop task.
 * Class node.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.08.2017
 */
public class Node {

    /**
     * reference on next element.
     */
    private Node next;

    /**
     * value.
     */
    private int value;

    /**
     * Constructor for class.
     * @param value value.
     * @param next next reference.
     */
    public Node(int value, Node next) {
        this.next = next;
        this.value = value;
    }

    /**
     * Method return next element.
     * @return next element.
     */
    public Node getNext() {
        return next;
    }

    /**
     * method set next element.
     * @param next next element.
     */
    public void setNext(Node next) {
        this.next = next;
    }


}
