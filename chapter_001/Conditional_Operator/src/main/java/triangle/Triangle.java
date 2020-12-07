package triangle;

/**
 * Class for calculate triangle square.
 */
public class Triangle {

	/**
	 * Point a.
	 */
	private Point a;

	/**
	 * Point b.
	 */
	private Point b;

	/**
	 * Point c.
	 */
	private Point c;

	/**
	 *Constructor class with params.
	 * @param a point a of triangle.
	 * @param b point b of triangle.
	 * @param c point c of triangle.
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 *Method for calculate of area of triangle.
	 * @return area of triangle.
	 */
	public double area() {
		double sideA = this.a.distanceTo(this.b);
		double sideB = this.a.distanceTo(this.c);
		double sideC = this.b.distanceTo(this.c);
		double area;

		if ((sideA + sideB) > sideC & (sideB + sideC) > sideA & (sideC + sideA) > sideB) {
			double perimeter = (sideA + sideB + sideC) / 2;
			area = Math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC));
		} else {
			area = -1;
		}
		return area;
	}
}