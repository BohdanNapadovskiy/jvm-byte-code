package square;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class SquareTest {

    /**
     *
     */
    @Test
    public void whenAllCorrect() {
        final int firstNumber = 1;
        final int secondNumber = 2;
        final int thirdNumber = 3;

        Square square = new Square(firstNumber, secondNumber, thirdNumber);
        final double check = 11;
        final int x = 2;
		double result = square.calculate(x);
        Assert.assertThat(result, is(check));
    }
}