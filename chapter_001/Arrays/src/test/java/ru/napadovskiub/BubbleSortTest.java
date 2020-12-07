package ru.napadovskiub;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class BubbleSortTest {

	/**
	 *
	 */
	@Test
    public void whenAllCorrect() {
        BubbleSort bubbleSort = new BubbleSort();
 		final int[] checked = new int[]{0, 1, 2, 3, 4, 5};
		final int[] result = new int[]{3, 5, 0, 2, 4, 1};
		bubbleSort.sortArray(result);
		Assert.assertThat(result, is(checked));

    }
}