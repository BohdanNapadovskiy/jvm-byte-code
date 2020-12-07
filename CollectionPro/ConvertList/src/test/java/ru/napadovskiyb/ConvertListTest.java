package ru.napadovskiyb;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 *
 */
public class ConvertListTest {

    /**
     *
     */
    @Test
    public void whenConvertSucsesThenLastElementMustBeFirst()  {
        ConvertList<Integer> convertList = new ConvertList<>();

        final Node firstNode = new Node(1, null);
        final Node secondNode = new Node(2, null);
        final Node firthNode = new Node(3, null);

        convertList.add(firstNode);
        convertList.add(secondNode);
        convertList.add(firthNode);

        convertList.convert(firstNode);
        assertThat(convertList.returnLastElement(), is(firstNode));

    }


}