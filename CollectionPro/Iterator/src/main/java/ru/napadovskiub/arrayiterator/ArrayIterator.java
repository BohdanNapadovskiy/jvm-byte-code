package ru.napadovskiub.arrayiterator;

import java.util.Iterator;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 22.06.2017
 */
public class ArrayIterator implements Iterator {

    /**
     * Main array.
     */
    private final int[][] array;

    /**
     *Number of lines.
     */
    private int lines = 0;

    /**
     *Number of cells.
     */
    private int index = 0;

    /**
     *Constructor for Array Iterator class.
     * @param array main array.
     */
    public ArrayIterator(final int[][] array) {

        this.array = array;

    }

    /**
     *Method check can move in array.
     * @return result.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;

        if ((this.lines >= this.array.length - 1) && (this.index >= this.array[this.lines].length - 1)) {
            result = false;
        }

        return result;
    }


    /**
     * Method take next value from array.
     * @return result.
     */
    @Override
    public Object next() {
       // Object result = null;
        if (index == array[lines].length && lines < array.length - 1) {
            lines++;
            index = 0;
        }
        return array[lines][index++];
    }
}
