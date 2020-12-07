package ru.napadovskiub.iteratorofiterators;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 01.07.2017
 */
public class IteratorOfIteratorsTest {

    /**
     *
     */
    private final List<Integer> firstList = Arrays.asList(4, 2, 0, 4, 6, 4, 9);

    /**
     *
     */
    private Iterator<Integer> firsIterator = firstList.iterator();

    /**
     *
     */
    private final List<Integer> secondList = Arrays.asList(0, 9, 8, 7, 5);

    /**
     *
     */
    private Iterator<Integer> secondIterator = secondList.iterator();

    /**
     *
     */
    private  final  List<Integer> thirdList = Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4);

    /**
     *
     */
    private Iterator<Integer> thirdIterator = thirdList.iterator();

    /**
     *
     */
    @Test
    public void whenConvertIsCorrectThenReturnValue() {

       List<Iterator<Integer>>  listIt = Arrays.asList(firsIterator, secondIterator, thirdIterator);
        Iterator<Iterator<Integer>> it =  listIt.iterator();

        IteratorOfIterators iteratorOfIterators = new IteratorOfIterators(it);

        Iterator<Integer> result = iteratorOfIterators.convert(it);
        result.next();
        result.next();
        assertThat(result.next(), is(thirdIterator));
    }

    /**
     *
     */
    @Test
    public void whenHasNextIsCorrectThenReturnTrue() {

        List<Iterator<Integer>>  listIt = Arrays.asList(firsIterator, secondIterator, thirdIterator);
        Iterator<Iterator<Integer>> it =  listIt.iterator();

        IteratorOfIterators iteratorOfIterators = new IteratorOfIterators(it);

        Iterator<Integer> result = iteratorOfIterators.convert(it);
        assertThat(result.hasNext(), is(true));
    }

}