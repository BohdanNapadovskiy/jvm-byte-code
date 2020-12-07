package triangle;

import org.junit.Assert;

import static org.hamcrest.core.Is.is;

import org.junit.Test;


/**
 *
 */
public class TriangleTest {

	/**
	 *
	 */
	@Test
    public void whenRightCoordinateShouldReturnArea() {
		//assign block
		final Point vertexA = new Point(0, 0.0);
		final Point vertexB = new Point(0, 3);
		final Point vertexC = new Point(4, 0);
		final double check = 6.0;
		Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
		//act block
		double result = triangle.area();
		//assert block
		Assert.assertThat(result, is(check));
    }

	/**
	 *
	 */
    @Test
    public void whenWrongCoordinateShouldReturnArea() {
		//assign block
		final Point vertexA = new Point(0.0, 0.0);
		final Point vertexB = new Point(0.0, 0.0);
		final Point vertexC = new Point(3.0, 26.0);
		final double check = -1.0;
		Triangle triangle = new Triangle(vertexA, vertexB, vertexC);
		//act block
		double result = triangle.area();
		//assert block
		Assert.assertThat(result, is(check));
    }
}