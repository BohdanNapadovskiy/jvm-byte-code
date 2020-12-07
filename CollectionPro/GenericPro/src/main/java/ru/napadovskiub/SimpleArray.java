package ru.napadovskiub;

import java.util.NoSuchElementException;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 03.07.2017
 * @param <T> generic.
 */
public class SimpleArray<T> {

    /**
     *Array for elements.
     */
    private Object[] objects;

    /**
     *cursor on current index of Array.
     */
    private int currentPosition;

    /**
     *Constructor class with size.
     * @param size array size.
     */
    public SimpleArray(int size) {
        objects = new Object[size];
    }

    /**
     *Method add value to array.
     * @param value value.
     */
    public void add(T value) {
        this.objects[this.currentPosition] = value;
        this.currentPosition++;
    }

    /**
     *Method return value by index.
     * @param index index from array.
     * @return value.
     */
    public T get(int index) {
        return  (T) this.objects[index];
    }

    /**
     * Method update oldValue newValue.
     * @param oldValue oldValue.
     * @param newValue newValue.
     * @throws NoSuchElementException exception.
     */
    public void update(T oldValue, T newValue)  throws NoSuchElementException {
        boolean check = false;

        for (int i = 0; i < this.objects.length; i++) {
            if (this.objects[i] != null) {
                if (this.objects[i].equals(oldValue)) {
                    this.objects[i] = newValue;
                    check = true;
                    break;
                }
            }
        }
        if (!check) {
            throw new NoSuchElementException("No such element");
        }
    }

    /**
     *Method return new array.
     * @return new array.
     */
    private Object[] copyArray() {
        Object[] result = new Object[this.objects.length - 1];
        int counter = 0;
        for (int i = 0; i < this.objects.length;) {
            if (this.objects[i] != null) {
                result[counter] = this.objects[i];
                counter++;
            }
            i++;
        }
        return result;
    }

    /**
     *Method delete value from Array.
     * @param value value for delete.
     * @throws NoSuchElementException exception.
     * @return result new array with new elements.
     */
    public Object[] delete(T value)  throws  NoSuchElementException {
        for (int i = 0; i < this.objects.length; i++) {
            if (this.objects[i].equals(value)) {
                objects[i] = null;
            }
        }

        Object[] result = copyArray();

        return result;
    }

}
