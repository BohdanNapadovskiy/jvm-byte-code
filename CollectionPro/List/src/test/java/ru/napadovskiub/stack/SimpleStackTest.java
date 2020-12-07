package ru.napadovskiub.stack;

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
public class SimpleStackTest {

    /**
     *
     */
    @Test
    public void whenPeekThanReturnElement() {
        SimpleStack<String>  simpleStack = new SimpleStack<String>();
        simpleStack.push("Барсик");
        simpleStack.push("Моська");

        String checkValue = simpleStack.peek();

        assertThat(checkValue, is("Моська"));

    }

    /**
     *
     */
    @Test
    public void whenPopThanReturnElementAndResizeStack() {
        SimpleStack<String>  simpleStack = new SimpleStack<String>();
        simpleStack.push("Барсик");
        simpleStack.push("Моська");

        String checkValue = simpleStack.pop();


        assertThat(simpleStack.getSize(), is(1));

    }

    /**
     *
     */
    @Test
    public void whenPushElementThanReturnValue() {
        SimpleStack<String>  simpleStack = new SimpleStack<String>();
        simpleStack.push("Барсик");
        simpleStack.push("Моська");

        String checkValue = simpleStack.pop();

        assertThat(checkValue, is("Моська"));
    }

}