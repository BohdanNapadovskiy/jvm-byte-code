package ru.napadovskiub.queue;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 05.07.2017
 */
public class SimpleQueueTest {

    /**
     *
     */
    @Test
    public void whenAddElementThanReturnValue() {
        SimpleQueue<String>  simpleQueue = new SimpleQueue<String>();
        simpleQueue.offer("Барсик");
        simpleQueue.offer("Моська");

        String checkValue = simpleQueue.peek();

        assertThat(checkValue, is("Барсик"));
    }

    /**
     *
     */
    @Test
    public void whenPeekElementThanReturnElement() {
        SimpleQueue<String>  simpleQueue = new SimpleQueue<String>();
        simpleQueue.offer("Барсик");
        simpleQueue.offer("Моська");

        String checkValue = simpleQueue.peek();

        assertThat(simpleQueue.getSize(), is(2));

    }

    /**
     *
     */
    @Test
    public void whenPollElementThanReturnElementAndResizeQue() {
        SimpleQueue<String>  simpleQueue = new SimpleQueue<String>();
        simpleQueue.offer("Барсик");
        simpleQueue.offer("Моська");

        String checkValue = simpleQueue.poll();

        assertThat(simpleQueue.getSize(), is(1));
        assertThat(checkValue, is("Барсик"));


    }


}