package ru.napadovskiub.cycletest;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 11.07.2017
 * @param <T> generic.
 */
public class Node<T> {

    /**
     * value of node.
     */
    private T value;

    /**
     * next node.
     */
    private Node<T> next;

    /**
     *Constructor for class with value.
     * @param value for node.
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Method return next value.
     * @return next value.
     */
    public T getNext() {
        return (T) this.next;
    }

    /**
     *Method set next value.
     * @param nextValue next value.
     */
    public void setNext(T nextValue) {
        this.next = (Node<T>) nextValue;

    }

    /**
     *Method for check the cycle.
     * @param first first value.
     * @return result.
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        Node tmpValue = first.next;
        Node tmpValueNext = first.next.next;
        while (tmpValueNext != null) {
            if (tmpValue.equals(first) || tmpValueNext.equals(first)) {
                result = true;
                break;

            }
            tmpValue = tmpValue.next;
            tmpValueNext = tmpValue.next.next;
        }
        return result;
    }

}
