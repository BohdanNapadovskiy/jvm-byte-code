package square;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class FactorialTest {

	/**
	 *
	 */
	@Test
	public void whenAllCorrect() {
		final int calculate = 5;
		final int check = 120;
		Factorial fact  = new Factorial(calculate);
		int result = fact.calculate();
		Assert.assertThat(result, is(check));
	}

	/**
	 *
	 */
	@Test
	public void whenUnCorrect() {
		final int calculate = 5;
		final int check = 120;
		Factorial fact  = new Factorial(calculate);
		int result = fact.calculate();
		Assert.assertThat(result, is(check));
	}
}