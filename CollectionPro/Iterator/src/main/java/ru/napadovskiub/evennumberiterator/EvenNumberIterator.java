package ru.napadovskiub.evennumberiterator;


import java.util.Iterator;

/**
 * Even number iterator.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 22.06.2017
 */
public class EvenNumberIterator implements Iterator {

    /**
     *Main array.
     */
    private int[] array;

    /**
     * index of array.
     */
    private int cursor = 0;

    /**
     *Constructor for Array Iterator class.
     * @param array main array.
     */
    public EvenNumberIterator(int[] array) {
        this.array = array;

    }

    /**
     * Method check current position.
     * @return return cursor if number is even.
     */
    private int checkEvenNumber() {
        int position = this.cursor;
        for (; position < this.array.length; position++) {
            if (this.array[position] % 2 == 0) {
                return position;
            }
        }

        return  -1;
    }


    /**
     *Method check can move in array.
     * @return result.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (checkEvenNumber() != -1) {
           result = true;
       }

        return result;
    }

    /**
     * Method take next value from array.
     * @return result.
     */
    @Override
    public Object next() {
        this.cursor = checkEvenNumber();
        return  this.array[this.cursor++];

    }
}
