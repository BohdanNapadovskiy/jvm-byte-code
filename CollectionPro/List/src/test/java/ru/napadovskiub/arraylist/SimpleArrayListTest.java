package ru.napadovskiub.arraylist;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by Программист on 05.07.2017.
 */
public class SimpleArrayListTest {

    /**
     *
     */
    @Test
    public void whenAddNewElementThanReturnValue() {
        String firstValue = "Иван";
        String secondValue = "Михаил";

        SimpleArrayList<String> arrayList = new SimpleArrayList<String>();

        arrayList.add(firstValue);
        arrayList.add(secondValue);

        assertThat(arrayList.get(0), is(firstValue));
    }

    /**
     *
     */
    @Test
    public void whenGetThenReturnValue() {
        String firstValue = "Иван";
        String secondValue = "Михаил";

        SimpleArrayList<String> arrayList = new SimpleArrayList<String>();

        arrayList.add(firstValue);
        arrayList.add(secondValue);

        assertThat(arrayList.get(0), is(firstValue));

    }

    /**
     *
     */
    @Test
    public void whenIteratorHasNextThenReturnTrue() {
        String firstValue = "Иван";
        String secondValue = "Михаил";

        SimpleArrayList<String> arrayList = new SimpleArrayList<String>();

        arrayList.add(firstValue);
        arrayList.add(secondValue);

        arrayList.get(0);
        boolean result = arrayList.iterator().hasNext();

        assertThat(result, is(true));


    }


}