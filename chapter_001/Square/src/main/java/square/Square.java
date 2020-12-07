package square;

/**
 *Class for calculate square.
 */
public class Square {
	/**
	 * coordinate point a.
	 */
	private double a;

	/**
	 * coordinate point b.
	 */
	private double b;
	/**
	 * coordinate point c.
	 */
	private double c;

	/**
	 * Constructor for class.
	 * @param a number a.
	 * @param b number b.
	 * @param c number c.
	 */
	public Square(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 *Method calculate square.
	 * @param x number for calculate.
	 * @return return result.
	 */
	public float calculate(int x) {
		return (float) (this.a * Math.pow(x, 2) + this.b * x + this.c);
		}

	/**
	 * Main method to calculate.
	 * @param args main args from console
	 */
	public static void main(String[] args) {
		final int firstArgs = 3;
		int x1   = 	Integer.valueOf(args[firstArgs]);
		final int secondArgs = 4;
		int x2   = 	Integer.valueOf(args[secondArgs]);
		final int thirdArgs = 5;
		int step = 	Integer.valueOf(args[thirdArgs]);
		final int firstNumber = 0;
		final int secondNumber = 1;
		final int thirdNumber = 2;

		Square square = new Square(Double.valueOf(args[firstNumber]), Double.valueOf(args[secondNumber]), Double.valueOf(args[thirdNumber]));

		for (int count = x1; count < x2; count = count + step) {
			float result = square.calculate(count);
			System.out.println(result);
		}
	}
}

