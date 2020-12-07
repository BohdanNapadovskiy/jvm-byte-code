package ru.napadovskiub.evennumberiterator;

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
public class EvenNumberIteratorTest {
    /**
     *
     */
    @Test
    public void whenAllCorrectThenReturnValue() {

        final int[] testArray = new int[] {1, 2, 4, 5};

        final int checked = 4;

        EvenNumberIterator evenNumberIterator  = new EvenNumberIterator(testArray);

        evenNumberIterator.next();
        int result = (Integer) evenNumberIterator.next();

        assertThat(result, is(checked));

    }

    /**
     *
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayDontHaveEvenNumberThenReturnException() {
        final int[] testArray = new int[] {1, 3, 5, 7};

        EvenNumberIterator evenNumberIterator  = new EvenNumberIterator(testArray);

        int result = (Integer) evenNumberIterator.next();

    }

    /**
     *
     */
    @Test
    public void whenArrayDontHaveNextThenReturnFalse() {
        final int[] testArray = new int[] {1, 2, 5, 7};

        EvenNumberIterator evenNumberIterator  = new EvenNumberIterator(testArray);

        evenNumberIterator.next();
        boolean result = evenNumberIterator.hasNext();

        assertThat(result, is(false));

    }

    /**
     *
     */
    @Test
    public void whenArrayHaveNextThenReturnTrue() {
        final int[] testArray = new int[] {1, 2, 3, 4};

        EvenNumberIterator evenNumberIterator  = new EvenNumberIterator(testArray);

        evenNumberIterator.next();
        boolean result = evenNumberIterator.hasNext();

        assertThat(result, is(true));

    }


}