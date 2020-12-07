package ru.napadovskiy.threadsafestorage.linkedlist;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.napadovskiy.threadsafestorage.SimpleContainer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <E> generic.
 * @since 08.07.2017
 */

@ThreadSafe
public class ThreadSafeSimpleLinkedList<E> implements SimpleContainer<E> {

    /**
     * size of list.
     */
    @GuardedBy("this")
    private int size = 0;

    /**
     * first element.
     */
    @GuardedBy("this")
    private SimpleCell<E> first;

    /**
     * last element.
     */
    @GuardedBy("this")
    private SimpleCell<E> last;

    /**
     * Method add objects too list.
     * @param o objects too adds.
     */
    @Override
    public void add(Object o) {
        synchronized (this) {
            final SimpleCell<E> tmpCell = this.last;
            final SimpleCell<E> newNode = new SimpleCell<E>(tmpCell, (E) o, null);
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
     * method return size of list.
     * @return size of list.
     */
    public synchronized int getSize() {
        return this.size;
    }

    /**
     * Method find element in list be index.
     * @param index for search
     * @return find element.
     */
    public SimpleCell<E> findElement(int index) {
        synchronized (this) {
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
    }

    /**
     * method check index.
     * @param index index.
     * @return result.
     */
    private synchronized boolean checkIndex(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
       return index < this.size;
    }

    /**
     * Method remove element from list.
     * @param index index for search.
     */
    public synchronized void removeElement(int index) {
        if (checkIndex(index)) {
            SimpleCell<E> cellForDelete = findElement(index);
            if (cellForDelete.prev != null) {
                cellForDelete.prev.next = cellForDelete.next;
            }
            if (cellForDelete.next != null) {
                cellForDelete.next.prev = cellForDelete.prev;
            }
            size--;
       }
    }

    /**
     * Method get value from list.
     * @param index for search
     * @return value.
     */
    @Override
    public synchronized E get(int index) {
        checkIndex(index);
        return findElement(index).item;
    }

    /**
     *Method return iterator for list.
     * @return iterator.
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
         *previus item.
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
}
