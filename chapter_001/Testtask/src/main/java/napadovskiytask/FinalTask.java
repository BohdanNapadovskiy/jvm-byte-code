package napadovskiytask;

/**
	*Class FinalTask для проверки вхождения подстроки в строке.
	*@author napadovskiy
	*@since 17.08.2016
	*@version 1
*/
public class FinalTask {

	  /**
	*@param origin string for search.
	*@param sub string.
	*@return findString return true if origin contains sub and false if not
	*/
	public boolean findSubString(String origin, String sub) {
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        int counter = 0;
        int indFind = 0;
        boolean findString = false;

        for (int i = 0; i < subArray.length; i++) {
            char symbol = subArray[i];
            for (int j = indFind; j < originArray.length; j++) {
                if (symbol == originArray[j]) {
                    indFind = ++j;
                    counter++;
                    break;
                } else {
                    counter = 0;
                }
            }
        }
        if (counter == subArray.length) {
            findString = true;
        }
        return findString;
	}
}