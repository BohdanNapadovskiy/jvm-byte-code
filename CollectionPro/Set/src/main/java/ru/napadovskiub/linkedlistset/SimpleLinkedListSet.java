package ru.napadovskiub.linkedlistset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <E> generic.
 * @since 10.07.2017
 */
public class SimpleLinkedListSet<E> implements Iterable<E> {

    /**
     * size of list.
     */
    private int size = 0;

    /**
     * first element.
     */
    private SimpleCell<E> first;

    /**
     * last element.
     */
    private SimpleCell<E> last;

    /**
     * Method add objects too list.
     * @param e objects too adds.
     */
    public void add(E e) {
        if (checkNewElement(e)) {
            final SimpleCell<E> tmpCell = this.last;
            final SimpleCell<E> newNode = new SimpleCell<E>(tmpCell, e, null);
            this.last = newNode;
            if (tmpCell == null) {
                this.first = newNode;
            } else {
                tmpCell.next = newNode;
            }
            this.size++;
        }
    }

    /**
     * Method return list size.
     * @return result.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Method return element by index.
     * @param index index for find.
     * @return result.
     */
    public SimpleCell<E> get(int index) {
        if (index < this.size) {
            SimpleCell<E> tmpCell = this.first;
            for (int i = 0; i < index; i++) {
                tmpCell = tmpCell.next;
            }
            return  tmpCell;

        } else {
            SimpleCell<E> tmpCell = this.last;
            for (int i = size - 1; i > index; i--) {
                tmpCell = tmpCell.prev;
            }
            return tmpCell;

        }
    }

    /**
     * Method check element.
     * @param element element for search.
     * @return result.
     */
    private boolean checkNewElement(E element) {
        boolean result = true;
        SimpleCell<E> tmpCell = this.first;

        for (int i = 0; i < this.size; i++) {
            if (element.equals(tmpCell.item)) {
                result = false;
                break;
            }
            tmpCell = tmpCell.next;
        }
        return  result;

    }

    /**
     * Inner class for add value in list.
     * @param <E> generic.
     */
    private class SimpleCell<E> {

        /**
         * item.
         */
        private E item;

        /**
         * next item.
         */
        private SimpleCell<E> next;

        /**
         *previous item.
         */
        private SimpleCell<E> prev;

        /**
         *Constructor for inner class.
         * @param prev previus element.
         * @param element current element.
         * @param next nex element.
         */
        SimpleCell(SimpleCell<E> prev, E element, SimpleCell<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            /**
             * current index for iterator.
             */
            private int currentIndex = 0;

            /**
             * method check next position.
             * @return result.
             */
            @Override
            public boolean hasNext() {
                return  this.currentIndex < size;

            }

            /**
             * Method return value and move current index.
             * @return value.
             */
            @Override
            public Object next() {
                if (hasNext()) {
                    int current = this.currentIndex;
                    this.currentIndex++;
                    return get(current);
                }
                throw new NoSuchElementException();
            }
        };
    }
}
