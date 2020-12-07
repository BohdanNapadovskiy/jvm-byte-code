package ru.napadovskiub.fastset;

import org.junit.Test;
import ru.napadovskiub.arrayset.SimpleArraySet;

import java.util.Random;


/**
 *
 */
public class FastArraySetTest {

    /**
     * Method generate array random string.
     * @return string array.
     */
    public String[] returnArrayRandomString() {
        final int arraySize = 1000;

        String[] array = new String[arraySize];

        Random rn = new Random();

        final int lengthString = 100;

        CreateRandomString createRandomString = new CreateRandomString();

        for (int i = 0; i < arraySize; i++) {
            int stringLength = rn.nextInt(lengthString);

            String tmpString = createRandomString.createRandomString(stringLength);

            array[i] = tmpString;
        }

        return array;
    }

    /**
     *
     */
    @Test
    public void whenInsertArrayStringThenReturnNanoSec() {
        String[] stringArray = returnArrayRandomString();

        FastArraySet<String> fastArraySet = new FastArraySet<String>();

        SimpleArraySet<String> simpleArraySet = new SimpleArraySet<String>();

        long startFast = System.nanoTime();

        long resultFast;

        for (String string: stringArray) {
            fastArraySet.add(string);
        }

        long endFast = System.nanoTime();

        resultFast =  (endFast  - startFast);

        long startSimple = System.nanoTime();

        long resultSimple;

        for (String string: stringArray) {
            simpleArraySet.add(string);
        }

        long endSimple = System.nanoTime();

        resultSimple = (endSimple - startSimple);

        System.out.println("Time for add in simple array in nano sec" + resultSimple);
        System.out.println("Time for add in fast array in nano sec" + resultFast);

    }


}