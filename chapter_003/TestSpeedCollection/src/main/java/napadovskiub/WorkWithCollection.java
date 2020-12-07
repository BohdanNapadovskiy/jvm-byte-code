package napadovskiub;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by napadovskiuB on 25.05.2017.
 */
public class WorkWithCollection {

    /**
     * start time for test.
     */
    private long start;

    /**
     * end time test.
     */
    private long end;

    /**
     *
     */
    private static final int LENGTHSTRING = 100;


    /**
     * Method add array to collection.
     * @param collection type of collection.
     * @param stringArray amount string.
     * @return result time the adding string to collection.
     */
    public long add(Collection<String> collection, String[] stringArray) {
        long result;

        this.start = System.nanoTime();

        for (String string: stringArray) {
            collection.add(string);
        }

        this.end = System.nanoTime();

        result = this.end - this.start;

        return result;

    }


    /**
     * Method add to collection strings.
     * @param collection type of collection.
     * @param amount amount string.
     * @return result time the adding string to collection.
     */
    public long add(Collection<String> collection, int amount) {
        CreateRandomString createRandomString = new CreateRandomString();

        long result;

        Random rn = new Random();

        int stringLength = rn.nextInt(this.LENGTHSTRING);

        this.start = System.nanoTime();

        for (int i = 0; i < amount; i++) {
            collection.add(createRandomString.createRandomString(stringLength));
        }

        this.end = System.nanoTime();

        result = this.end - this.start;

        return result;
    }


    /**
     * Method delete strings from collection.
     * @param collection type of collection.
     * @param amount amount of strings.
     * @return result time the adding string to collection.
     */
    public long delete(Collection<String> collection, int amount) {

        long result;

        Iterator<String> iterator = collection.iterator();

        int count = 0;

        this.start = System.nanoTime();

        while (iterator.hasNext() && count < amount) {
            iterator.next();
            iterator.remove();
            count++;
        }

        this.end = System.nanoTime();

        result = this.end - this.start;

        return result;

    }
}
