package ru.napadovskiyb.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <E> generic.
 * @since 08.07.2017
 */
@ThreadSafe
public class SimpleBlockingQueue<E>  {

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
    public  void add(Object o) {
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
     *Method add new element to queue.
     * @param e value.
     */
    public synchronized void offer(E e) {
        add(e);
    }

    /**
     * Method find element in list be index.
     * @param index for search
     * @return find element.
     */
    private SimpleCell<E> findElement(int index) {
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
     * Method get value from list.
     * @param index for search
     * @return value.
     */
    private synchronized E get(int index) {
        checkIndex(index);
        return findElement(index).item;
    }

    /**
     * method check index.
     * @param index index.
     * @return result.
     */
    private boolean checkIndex(int index) {
        synchronized (this) {
            if (index >= this.size) {
                throw new IndexOutOfBoundsException();
            }
            return index < this.size;
        }
    }

    /**
     * Method remove element from list.
     * @param index index for search.
     */
    private void removeElement(int index) {
        synchronized (this) {
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
    }

    /**
     * Method return first element.
     * @return value.
     */
    public synchronized E peek() {
        return get(0);
    }

    /**
     * Method return first element and delete it.
     * @return value.
     */
    public synchronized E poll() {
        E result = get(0);
        removeElement(0);
        return result;

    }

    /**
     * Inner class for add value in list.
     * @param <E> generic.
     */
    private final class SimpleCell<E> {

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
