package triangle;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 *
 */
public class MaxLengthTest {

	/**
	 *
	 */
	@Test
    public void whenRigthTriangleThenReturnMaxSide() {
		//assign block
		final Point pointA = new Point(0.0, 2.0);
		final Point pointB = new Point(3.0, 6.0);
		final Point pointC = new Point(0.0, 10.0);
		double sideA = pointA.distanceTo(pointB);
		double sideB = pointB.distanceTo(pointC);
		double sideC = pointA.distanceTo(pointC);
		final double check = 8.0;
		MaxLength maxLength = new MaxLength(sideA, sideB, sideC);
		//act block
		double result = maxLength.maxLength();
		//assert block
		Assert.assertThat(result, is(check));
    }
}