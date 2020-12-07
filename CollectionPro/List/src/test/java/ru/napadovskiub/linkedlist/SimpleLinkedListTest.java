package ru.napadovskiub.linkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Программист on 06.07.2017.
 */
public class SimpleLinkedListTest {

    /**
     *
     */
    @Test
    public void whenAddThanReturnValue() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("Барсик");
        simpleLinkedList.add("Васька");

        String checkValue = simpleLinkedList.get(1);
        assertThat(checkValue, is("Васька"));

    }

    /**
     *
     */
    @Test
    public void whenFindElementThanReturnValue() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("Моська");
        simpleLinkedList.add("Тюлень");

        String checkValue = simpleLinkedList.get(1);

        assertThat(checkValue, is("Тюлень"));


    }

    /**
     *
     */
    @Test
    public void whenRemoveElementThanLastElementIsPrevius() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("Моська");
        simpleLinkedList.add("Тюлень");
        simpleLinkedList.add("Барсик");


        simpleLinkedList.removeElement(2);
        String checkValue = simpleLinkedList.get(simpleLinkedList.getSize() - 1);

        assertThat(checkValue, is("Тюлень"));
    }

    /**
     *
     */
    @Test
    public void whenIteratorHasNextThanReturnValue() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("Моська");
        simpleLinkedList.add("Тюлень");
        simpleLinkedList.add("Барсик");

        Iterator<String> iterator = simpleLinkedList.iterator();

        iterator.next();
        String result1 = iterator.next();


        assertThat(result1, is("Тюлень"));



    }
}