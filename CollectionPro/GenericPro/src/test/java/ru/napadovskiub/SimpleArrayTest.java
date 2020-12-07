package ru.napadovskiub;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Package of CollectionPro testTask.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 03.07.2017
 **/
public class SimpleArrayTest {

    /**
     *
     */
    @Test
    public void whenAddStringThenReturnString() {
        final int sizeofArray = 2;
        SimpleArray<String> simpleArray = new SimpleArray<String>(sizeofArray);
        String stringForTest = "testString";
        String stringForTest1 = "Gh";

        simpleArray.add(stringForTest);
        simpleArray.add(stringForTest1);

        assertThat(simpleArray.get(1), is(stringForTest1));
    }

    /**
     *
     */
    @Test
    public void whenDeleteElementThenReturnArray() {
        final int sizeofArray = 2;
        SimpleArray<String> simpleArray = new SimpleArray<String>(sizeofArray);
        String stringForTest = "testString";
        String stringForTest1 = "Gh";

        simpleArray.add(stringForTest);
        simpleArray.add(stringForTest1);

        assertThat(simpleArray.get(1), is(stringForTest1));

    }

}