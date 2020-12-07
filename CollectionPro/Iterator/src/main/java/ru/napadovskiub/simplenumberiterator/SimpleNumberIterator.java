package ru.napadovskiub.simplenumberiterator;

import java.util.Iterator;

/**
 * Even number iterator.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 22.06.2017
 */
public class SimpleNumberIterator implements Iterator {

    /**
     * Main array.
     */
    private int[] array;

    /**
     * array cursor.
     */
    private int cursor = 0;

    /**
     *Constructor for Array Iterator class.
     * @param array main array.
     */
    public SimpleNumberIterator(int[] array) {
        this.array = array;

    }

    /**
     * Method checks number is prime.
     * @param numberForCheck number for check.
     * @return result.
     */
    private boolean checkNumber(int numberForCheck) {
        boolean result = true;
        if (numberForCheck >= 2) {
            for (int i = 2; i * i <= numberForCheck; i++) {
                if (numberForCheck % i == 0) {
                    result = false;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     *Method checks the prime number and return position.
     * @return position.
     */
    private int checkSimpleNumber() {
        int position = this.cursor;
        for (; position < this.array.length; position++) {
            if (checkNumber(this.array[position])) {
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
        if (checkSimpleNumber() != -1) {
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
        this.cursor = checkSimpleNumber();
        return  this.array[this.cursor++];
    }
}
