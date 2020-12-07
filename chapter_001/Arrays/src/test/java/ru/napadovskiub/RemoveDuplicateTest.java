package ru.napadovskiub;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class RemoveDuplicateTest {

    /**
     *
     */
    @Test
    public void whenAllCorrect() {
        String[] inputString = {"а", "б", "а", "б", "а", "г", "а", "л", "а", "м", "а", "г", "а"};
		String[] checked = {"а", "б", "г", "л", "м"};

		RemoveDuplicate resultString = new RemoveDuplicate();
        String[] result = resultString.removeDuplicate(inputString);

        Assert.assertThat(result, is(checked));

    }
}