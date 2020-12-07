package ru.napadovskiub.arrayiterator;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Package of chapter_003 testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 15.06.2017
 */
public class ArrayIteratorTest {

    /**
     *
     */
    @Test
    public void whenTestNextIsRightThenReturnValue() {
        final int[][] testArray = new int[][] {{1, 2},
                                               {4, 5}};
        final int checked = 5;

        ArrayIterator arrayIterator  = new ArrayIterator(testArray);

        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        int result = (Integer) arrayIterator.next();

        assertThat(result, is(checked));
    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenTestNextIsUnCorrectThenReturnException() {
        final int[][] testArray = new int[][] {{1, 2},
                                               {3}};

        ArrayIterator arrayIterator  = new ArrayIterator(testArray);

        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        int result = (Integer) arrayIterator.next();
    }

    /**
     *
     */
    @Test
    public void whenTestHasNextAndAllCorrectThenReturnTrue() {
        final int[][] testArray = new int[][] {{1, 2},
                                               {4, 5}};

        ArrayIterator arrayIterator  = new ArrayIterator(testArray);

        arrayIterator.next();
        boolean result = arrayIterator.hasNext();

        assertThat(result, is(true));

    }

    /**
     *
     */
    @Test
    public void whenTestHasNextAndUnCorrectThenReturnFalse() {
        final int[][] testArray = new int[][] {{1, 2},
                                               {4, 5}};

        ArrayIterator arrayIterator  = new ArrayIterator(testArray);

        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        arrayIterator.next();
        boolean result = arrayIterator.hasNext();

        assertThat(result, is(false));

    }

}