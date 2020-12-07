package square;

/**
	*Class Factorial для определения факториала числа.
	*@author napadovskiy
	*@since 03.08.2016
	*@version 1
*/
public class Factorial {

	/**
	 *
	 */
	private int number;

	/**
	 *Constructor for class.
	 * @param number number for calculate.
	 */
	public Factorial(int number) {
		this.number = number;
	}

	/**
		*Определение факториала.
		*@return результат.
	*/
	public int calculate() {
		int tmpCount = 1;
		if (this.number != 0) {
			for (int count = 1; count <= this.number; count++) {
				tmpCount = tmpCount * count;
			}
		}
		return tmpCount;
	}
}


