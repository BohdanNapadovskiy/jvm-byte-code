package ru.napadovskiub.iteratorofiterators;

import java.util.Iterator;

/**
 * Even number iterator.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 01.07.2017
 */
public class IteratorOfIterators implements ConvertIterator, Iterator {

    /**
     * iterator of iterators.
     */
    private Iterator<Iterator<Integer>> iteratorOfIterator;

    /**
     *current iterator.
     */
    private Iterator<Integer> currentIterator;

    /**
     *Method convert iterator of iterators to iterator.
     * @param it iterator of iterators.
     * @return iterator
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.iteratorOfIterator = it;
        if (this.iteratorOfIterator.hasNext()) {
            this.currentIterator.next();
        }
        return this;
    }

    /**
     *Constructor class.
     * @param it iterator of iterators.
     */
    public IteratorOfIterators(Iterator<Iterator<Integer>> it) {
        this.iteratorOfIterator = it;
        this.currentIterator = it.next();
    }

    /**
     *Method check can move in iterator.
     * @return result.
     */
    @Override
    public boolean hasNext() {
        return (this.iteratorOfIterator.hasNext() || this.currentIterator.hasNext());
    }

    /**
     * Method take next value from iterator.
     * @return result.
     */
    @Override
    public  Iterator<Integer> next() {
        Iterator<Integer> result = this.currentIterator;
        if (this.iteratorOfIterator.hasNext()) {
            this.currentIterator = this.iteratorOfIterator.next();
        } else {
            this.currentIterator = null;
        }
        return result;

    }
}
