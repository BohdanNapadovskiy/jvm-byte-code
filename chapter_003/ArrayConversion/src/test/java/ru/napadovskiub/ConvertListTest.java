package ru.napadovskiub;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Napadovskiy Bohdan.
 */
public class ConvertListTest {

    /**
     * Method for test conversion from Array to List.
     */
    @Test
    public void whenConvertArrayThenReturnList() {
        ConvertList convertList = new ConvertList();

        final int[][]  checked = {{7, 4, 1},
                                  {8, 5, 2},
                                  {9, 6, 3}};

        final List<Integer> listForCheck = Arrays.asList(7, 4, 1, 8, 5, 2, 9, 6, 3);

        List<Integer> resultList = convertList.toList(checked);

        assertThat(resultList, is(listForCheck));

    }

    /**
     * Method for test conversion from List to Array.
     */
    @Test
    public void  whenConvertListThenReturnArray() {
        ConvertList convertList = new ConvertList();

        final List<Integer> list = Arrays.asList(7, 8, 10, 1, 5, 6, 4, 3, 2, 9);

        final int[][] expected = {{7, 8, 10},
                                  {1, 5, 6},
                                  {4, 3, 2},
                                  {9, 0, 0}};
        final int rows = 4;

        int[][] actual = convertList.toArray(list, rows);

        assertThat(actual, is(expected));

    }

    /**
     * Method for test conversion list of Arrays in List.
     */
    @Test
    public void  whenConvertListofArrayThenReturnList() {
        ConvertList convertList = new ConvertList();

        final List<int[]> list = new ArrayList<int[]>();

        final int[] firstArray = new int[]{1, 2, 3};
        final int[] secondArray = new int[]{4, 5, 6};

        final List<Integer> resultList = Arrays.asList(1, 2, 3, 4, 5, 6);


        list.add(firstArray);
        list.add(secondArray);

        List<Integer> actual = convertList.convert(list);

        assertThat(actual, is(resultList));

    }


}