package triangle;

/**
 * Class for calculate of distance to point.
 */
public class Point {

	/**
	 * coordinate x.
	 */
	private double x;

	/**
	 * coordinate x.
	 */
	private double y;

	/**
	 * Constructor for class with coordinate.
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Method for calculate distance.
	 * @param point for calculate.
	 * @return distance.
	 */
	public double distanceTo(Point point) {
		double distance = Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));

		return distance;
	}
}