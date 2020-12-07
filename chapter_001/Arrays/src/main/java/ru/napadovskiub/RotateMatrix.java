package ru.napadovskiub;

/**
*Class RotateMatrix для поворота массива.
*@author napadovskiy
*@since 08.08.2016
*@version 1
*/

class RotateMatrix {
	/**
	 * Method rotate matrix.
	 * @param matrix for rotate.
	 * @return rotate matrix.
	 */
    public int[][] rotateArray(int[][] matrix) {
		int[][] newMatrix = new int[matrix.length][matrix.length];
		int x = 0, y = 0;
		for (int j = 0; j < matrix.length; j++) {
			for (int i = matrix.length - 1; i >= 0; i--) {
				newMatrix[y][x] = matrix[i][j];
				x++;
			}
		y++; x = 0;
		}
	return newMatrix;
	}
}