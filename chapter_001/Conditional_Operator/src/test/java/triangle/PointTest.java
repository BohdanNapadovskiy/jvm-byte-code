package triangle;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;


/**
 *
 */
public class PointTest {

	/**
	 *
	 */
	@Test
    public void whenRightCoordinateShouldReturnDistance() {
		//assign block
		final Point firstPoint = new Point(0.0, 2.0);
		final Point secondPoint = new Point(3.0, 6.0);
		final double check = 5.0;
		//act block
		double result = firstPoint.distanceTo(secondPoint);
		//assert block
		Assert.assertThat(result, is(check));
    }

	/**
	 *
	 */
    @Test
    public void whenWrongCoordinateShouldReturnDistance() {
		//assign block
		final Point firstPoint = new Point(0.0, 0.0);
		final Point secondPoint = new Point(0.0, 0.0);
		final double check = 0.0;
		//act block
		double result = firstPoint.distanceTo(secondPoint);
		//assert block
		Assert.assertThat(result, is(check));
    }
}