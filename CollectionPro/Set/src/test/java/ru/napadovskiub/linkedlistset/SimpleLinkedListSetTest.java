package ru.napadovskiub.linkedlistset;

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
public class SimpleLinkedListSetTest {

    /**
     *
     */
    private SimpleLinkedListSet<String> simpleLinkedListSet = new SimpleLinkedListSet<>();

    /**
     *
     */
    @Test
    public void whenAddElementThenIncreaseSizeList() {

        final int sizeList = 3;
        simpleLinkedListSet.add("First");
        simpleLinkedListSet.add("Second");
        simpleLinkedListSet.add("Third");

        assertThat(simpleLinkedListSet.getSize(), is(sizeList));
    }


    /**
     *
     */
    @Test
    public void whenAddCopyElementThenNotIncreaseSizeList() {

        final int sizeList = 2;

        simpleLinkedListSet.add("First");
        simpleLinkedListSet.add("Second");
        simpleLinkedListSet.add("Second");

        assertThat(simpleLinkedListSet.getSize(), is(sizeList));
    }

}