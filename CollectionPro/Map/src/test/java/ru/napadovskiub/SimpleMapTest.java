package ru.napadovskiub;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 *
 */
public class SimpleMapTest {

    /**
     *
     */
    @Test
    public void whenInsertThenReturnValue() {
        SimpleMap<Integer, String> myMap = new SimpleMap<>();
        final Integer fifthElem = 5;
        final Integer fourthElem = 4;
        final Integer secondElem = 2;

        myMap.insert(fifthElem, "Пятый");
        myMap.insert(fourthElem, "Четвертый");
        myMap.insert(secondElem, "Второй");

        String checkValue = myMap.get(fourthElem);

        assertThat(checkValue, is("Четвертый"));

    }


    /**
     *
     */
    @Test (expected = NullPointerException.class)
    public void whenDeleteItemThenReturnNull() {
        SimpleMap<Integer, String> myMap = new SimpleMap<>();
        final Integer fifthElem = 5;
        final Integer fourthElem = 4;
        final Integer secondElem = 2;


        myMap.insert(fifthElem, "Пятый");
        myMap.insert(fourthElem, "Четвертый");
        myMap.insert(secondElem, "Второй");


        myMap.delete(fourthElem);
        String checkValue = myMap.get(fourthElem);

    }

}