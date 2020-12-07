package ru.napadovskiub.iteratorofiterators;

import java.util.Iterator;

/**
 * Even number iterator.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 01.07.2017
 */
public interface ConvertIterator {

    /**
     * Method convert iterators of iterator to iterator.
     * @param it iterators of iterator.
     * @return iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
