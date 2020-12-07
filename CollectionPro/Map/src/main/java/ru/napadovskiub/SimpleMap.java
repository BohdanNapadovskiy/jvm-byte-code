package ru.napadovskiub;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple map.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 17.07.2017
 * @param <T> key element.
 * @param <V> value by element.
 */
public class SimpleMap<T, V> implements MyMap<T, V> {

    /**
     *Default capacity for table.
     */
    private final int defaultCapacity = 4;

    /**
     * Table for element.
     */
    private Entry[] table;

    /**
     * current position.
     */
    private int currentPosition = 0;

    /**
     * size of table.
     */
    private int size;

    /**
     *Default constructor.
     */
    public SimpleMap() {
        this.table = new Entry[defaultCapacity];

    }

    /**
     *Constructor with capacity.
     * @param capacity capacity of table.
     */
    public SimpleMap(int capacity) {
        this.table = new Entry[capacity];
    }

    /**
     * Method calculate hash code.
     * @param key key for calculate.
     * @return hash code.
     */
    private static int hash(Object key) {
        int h;
        final int var = 16;
        h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> var);
    }

    /**
     * Method insert element too collection.
     * @param key key for add
     * @param value value element.
     * @return result.
     */
    @Override
    public boolean insert(T key, V value) {
        boolean result = true;
        this.currentPosition = indexFor(hash(key), table.length);
        resizeTable();
        if (table[this.currentPosition] == null) {
            table[this.currentPosition] = new Entry(key, value);
            this.size++;
        } else {
            result = false;
        }

        return result;

    }

    /**
     * Method return element from collection by key.
     * @param key key for search.
     * @return value from table.
     */
    @Override
    public V get(T key) {
        int index = indexFor(hash(key), table.length);

        return (V) table[index].getValue();

    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(T key) {
        boolean result = true;
        int index = indexFor(hash(key), table.length);
        if (this.table[index] != null) {
            this.table[index] = null;
            this.size--;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Method calculate index by hashCode.
     * @param h hashCode.
     * @param length table length.
     * @return return index for add.
     */
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    /**
     * Method resize table.
     */
    private void resizeTable() {
        final double kof = 0.75;
        if (this.table.length * kof <= this.currentPosition) {
            int newCapacity  = (this.table.length * 2);
            Entry<T, V>[] newArray = new Entry[newCapacity];
            for (int j = 0; j < this.table.length - 1; ++j) {
                Entry<T, V> checkElement;
                checkElement = this.table[j];
                if (checkElement != null) {
                    newArray[hash(checkElement.getKey()) & (newCapacity - 1)] = checkElement;
                }

            }
            this.table = newArray;
        }
    }

    /**
     * Inner class iterator.
     * @return iterator.
     */
    public Iterator iterator() {

        /**
         *Inner class iterator.
         */
        return new Iterator() {

           /**
            * current index.
            */
           private int index = 0;

           /**
            * method check next position.
            * @return result.
            */
           @Override
           public boolean hasNext() {
               return  this.index < size;
           }

           /**
            * Method return value and move current index.
            * @return value.
            */
           @Override
           public V next() {
               if (hasNext()) {
                   Integer current = this.index;
                   this.index++;
                   return get((T) current);
               }
               throw new NoSuchElementException();
           }
       };
   }


    /**
     * Inner class element.
     * @param <T> key element.
     * @param <V> value element.
     */
   private class Entry<T, V> {

       /**
        * key element.
        */
       private T key;

       /**
       * value element.
       */
       private V value;

       /**
        *Constructor with key and value.
        * @param key key element.
        * @param value value element.
        */
       Entry(T key, V value) {
           this.key = key;
           this.value = value;
       }

       /**
        * Method return key of element.
        * @return key.
        */
       public T getKey() {
            return key;
        }

       /**
        * Method return value of element.
        * @return value.
        */
       public V getValue() {
            return value;
       }
    }
}
