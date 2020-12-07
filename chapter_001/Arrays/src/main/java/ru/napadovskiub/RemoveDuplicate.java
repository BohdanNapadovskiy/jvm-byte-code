package ru.napadovskiub;

/**
	*Class RemoveDuplicate для удаления дублей из массива.
	*@author napadovskiy
	*@since 15.08.2016
	*@version 1
*/


public class RemoveDuplicate {
    /**
     *Create new array with length of inputString.
     *and fill it with unique elements;
     *and decrement counter
     *then create new array with length of counter
     * @param inputString string for remove duplicate.
     * @return result Array string.
     */
	public String[] removeDuplicate(String[] inputString) {
        String[] tempString = new String[inputString.length];

        int counter = 0;

        for (int i = 0; i < inputString.length; i++) {
            if (i == 0) {
                tempString[counter] = inputString[i];
                counter++;
            } else {
                if (!findeElement(tempString, inputString[i])) {
                    tempString[counter] = inputString[i];
                    counter++;
                }
            }
        }
		String[] resultString = new String[counter];
		for (int i = 0; i < counter; i++) {
			resultString[i] = tempString[i];
		}
		return resultString;
    }

    /**
     *find value in tempString
     *If found then it is not necessary to add.
     * @param tempString string for search.
     * @param value serch value.
     * @return result.
     */
    boolean findeElement(String[] tempString, String value) {
        boolean findeElement = false;
        for (int i = 0; i < tempString.length; i++) {
            if (tempString[i] != null && tempString[i].equals(value)) {
                findeElement = true;
                break;
            }
        }
        return findeElement;
    }
}
