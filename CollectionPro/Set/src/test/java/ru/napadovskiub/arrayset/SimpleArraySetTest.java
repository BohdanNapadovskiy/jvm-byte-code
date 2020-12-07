package ru.napadovskiub.arrayset;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.07.2017
 */
public class SimpleArraySetTest {

    /**
     *
     */
    private SimpleArraySet<String> simpleArraySet = new SimpleArraySet<String>(2);

    /**
     *
     */
    @Test
    public void whenAddNewElementTooSetThenIncreaseArray() {

        final int sizeArray = 3;

        this.simpleArraySet.add("First");
        this.simpleArraySet.add("Second");
        this.simpleArraySet.add("Third");

        assertThat(this.simpleArraySet.getSize(), is(sizeArray));

    }

    /**
     *
     */
    @Test
    public void whenAddNewSimeElementTooSetThenNotIncreaseArray() {

        final int sizeArray = 3;

        this.simpleArraySet.add("First");
        this.simpleArraySet.add("Second");
        this.simpleArraySet.add("Third");
        this.simpleArraySet.add("Third");

        assertThat(this.simpleArraySet.getSize(), is(sizeArray));

    }

}