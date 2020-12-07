package ru.napadovskiub.cycletest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Владелец on 10.07.2017.
 */
public class NodeTest {

    /**
     *
     */
    private final Node first = new Node(1);

    /**
     *
     */
    private final Node two = new Node(2);

    /**
     *
     */
    private final Node third = new Node(3);

    /**
     *
     */
    private final Node four = new Node(4);


    /**
     *
     */
    @Test
    public void whenListHasCycleThenReturnTrue() {

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        boolean result = first.hasCycle(first);

        assertThat(result, is(true));

    }

    /**
     *
     */
    @Test

    public void whenListHasntCycleThenReturnFalse() {
        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        boolean result = first.hasCycle(first);

        assertThat(result, is(false));

    }

}