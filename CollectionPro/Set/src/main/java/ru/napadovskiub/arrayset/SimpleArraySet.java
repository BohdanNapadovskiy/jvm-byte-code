package ru.napadovskiub.arrayset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @param <T> generic.
 * @since 10.07.2017
 */
public class SimpleArraySet<T> implements Iterable<T> {

    /**
     *Main array.
     */
    private Object[] mainArray;

    /**
     * Default size array.
     */
    private final int defaultSize = 2;

    /**
     * array size.
     */
    private int size;

    /**
     * index too add element.
     */
    private int indexForWrite;


    /**
     * Constructor with default size of array.
     */
    public SimpleArraySet() {
        this.mainArray = new Object[defaultSize];
        this.indexForWrite = 0;
    }


    /**
     * Constructor with user size.
     * @param size array size.
     */
    public SimpleArraySet(int size) {
        this.mainArray = new Object[size];
        this.indexForWrite = 0;
    }


    /**
     *Method resize array.
     */
    private void resizeArray() {
        int newCapacity = (this.mainArray.length * 2);
        this.mainArray = Arrays.copyOf(this.mainArray, newCapacity);
    }

    /**
     * Method check element in array.
     * @param t element for find.
     * @return result.
     */
    public boolean checkElement(T t) {
        boolean result = true;
        for (int i = 0; i < this.mainArray.length; i++) {
            if (this.mainArray[i] != null) {
                if (this.mainArray[i].equals(t)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }


    /**
     *Method add element to array.
     * @param t element too ad.
     */
    public void add(T t) {
        if (this.indexForWrite == (this.mainArray.length)) {
            resizeArray();
        }
        if (checkElement(t)) {
            this.mainArray[this.indexForWrite] = t;
            this.indexForWrite++;
            this.size++;
        }
    }

    /**
     * Method return size array.
     * @return result.
     */
    public int getSize() {
        return this.size;
    }


    /**
     *Method return element by index.
     * @param index for search.
     * @return element.
     */
    public T getElement(int index) {
        return (T) this.mainArray[index];
    }


    /**
     * Class iterator.
     * @return iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * current index.
             */
            private int currentIndex;

            /**
             * Method check has next element.
             * @return result.
             */
            @Override
            public boolean hasNext() {
                return currentIndex < mainArray.length;
            }

            /**
             * Method return next element.
             * @return result.
             * @throws NoSuchElementException
             */
            @Override
            public T next() throws NoSuchElementException {
                if (hasNext()) {
                    int current = currentIndex;
                    this.currentIndex++;
                    return (T) mainArray[current];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
