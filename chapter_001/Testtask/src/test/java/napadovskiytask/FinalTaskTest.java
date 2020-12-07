package napadovskiytask;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class FinalTaskTest {

    /**
     *
     */
    @Test
    public void whenAllCorrect() {
        FinalTask finalTest = new FinalTask();
        String origin  = "а роза упала на лапу азора";
        String sub = "роза";
		boolean result = finalTest.findSubString(origin, sub);
		boolean checked = true;
		Assert.assertThat(result, is(checked));
    }

    /**
     *
     */
    @Test
    public void whenNotCorrect() {
        FinalTask finalTest = new FinalTask();
        String origin  = "а роза упала на лапу азора";
        String sub = "узора";
		boolean result = finalTest.findSubString(origin, sub);
		boolean checked = false;
		Assert.assertThat(result, is(checked));
    }
}