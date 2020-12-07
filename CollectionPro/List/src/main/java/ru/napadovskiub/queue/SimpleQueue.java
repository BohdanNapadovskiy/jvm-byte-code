package ru.napadovskiub.queue;

import ru.napadovskiub.linkedlist.SimpleLinkedList;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <E> generic.
 * @since 08.07.2017
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {

    /**
     *Method add new element to queue.
     * @param e value.
     */
    public void offer(E e) {
        add(e);
    }

    /**
     * Method return first element.
     * @return value.
     */
    public E peek() {
        return get(0);
    }

    /**
     * Method return first element and delete it.
     * @return value.
     */
    public E poll() {
        E result = get(0);
        removeElement(0);
        return result;

    }
}
