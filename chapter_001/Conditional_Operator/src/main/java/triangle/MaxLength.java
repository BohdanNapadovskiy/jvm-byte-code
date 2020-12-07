package triangle;

/**
 *Class  to find max length of triangle.
 */
public class MaxLength {

	/**
	 * A side triangle.
 	 */
	private double  sideA;

	/**
	 * B side triangle.
	 */
	private double sideB;

	/**
	 *C side triangle.
	 */
	private double sideC;

	/**
	 *Constructor for class with params.
	 * @param sideA side a.
	 * @param sideB side b.
	 * @param sideC side C.
	 */
	public MaxLength(double sideA, double sideB, double sideC) {

		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
		}

	/**
	 *Method find max length of triangle.
	 * @return side.
	 */
	public double maxLength() {
			double maxLengthSide = this.sideA;
			maxLengthSide = Math.max(this.sideA, Math.max(this.sideB, this.sideC));
			return maxLengthSide;
	}
}