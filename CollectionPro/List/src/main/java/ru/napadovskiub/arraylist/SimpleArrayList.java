package ru.napadovskiub.arraylist;

import ru.napadovskiub.SimpleContainer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 05.06.2017
 * @param <E> generic.
 */
public class SimpleArrayList<E> implements SimpleContainer<E> {

    /**
     *Main array.
     */
    private Object[] mainArray;

    /**
     * Default size array.
     */
    private final int defaultSize = 2;

    /**
     * current index.
     */
    private int currentIndex;

    /**
     * index too add element.
     */
    private int indexForWrite;


    /**
     *Default constructor for array.
     */
    public SimpleArrayList() {
        this.mainArray = new Object[defaultSize];
        this.currentIndex = 0;
        this.indexForWrite = 0;

    }

    /**
     *Constructor with size.
     * @param size array size.
     */
    public SimpleArrayList(int size) {
        this.mainArray = new Object[size];
        this.currentIndex = 0;
    }

    /**
     * Method to resize array if needed.
     */
    private void resizeArray() {
       int newCapacity = (this.mainArray.length * 2);
       this.mainArray = Arrays.copyOf(this.mainArray, newCapacity);
    }

    /**
     *Method add value too array.
     * @param value
     */
    @Override
    public void add(E value) {
        if (this.indexForWrite == (this.mainArray.length)) {
            resizeArray();
        }
        this.mainArray[this.indexForWrite++] = value;
    }

    /**
     * Method return value by index.
     * @param index for search.
     * @return value.
     * @throws NullPointerException exception.
     */
    @Override
    public E get(int index) throws NullPointerException {
        if (index < this.mainArray.length) {
            this.currentIndex = index;
            return (E) this.mainArray[index];

        }
        throw new NullPointerException();

    }


    /**
     *Method for iterator.
     * @return iterator.
     */
    @Override
    public Iterator iterator() {

        return new Iterator() {

            /**
             * method check can we move next.
             * @return result.
             */
            @Override
            public boolean hasNext() {
                return currentIndex < (mainArray.length - 1);
            }

            /**
             * Method return current value if we can move.
             * @return value.
             * @throws NoSuchElementException exception.
             */
            @Override
            public Object next() throws NoSuchElementException {
                if (hasNext()) {
                    int current = currentIndex;
                    currentIndex++;
                    return mainArray[current];
                }
                throw new NoSuchElementException();

            }
        };
    }



}
