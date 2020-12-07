package napadovskiub;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by napadovskiuB on 25.05.2017.
 */
public class PerfomensSpeed {

    /**
     * Array list for speed testing.

     */
    private ArrayList<String> arrayList = new ArrayList<String>();

    /**
     * Linked list for checking.
     */
    private LinkedList<String> linkedList = new LinkedList<String>();

    /**
     * Tree for speed testing.

     */
    private TreeSet<String> treeSet = new TreeSet<String>();

    /**
     * Number of lines for add to Collection.
     */
    private static final int NUMBERLINES = 1000000;

    /**
     *
     */
    private static final int NUMBERFORCONVERTNANO = 1000000000;
    /**
     *  Class with method's for checking speed.
     */
    private  WorkWithCollection workWithCollection  = new WorkWithCollection();

    /**
     * method test speed add string to collection.
     */
    public void testAdd() {

        System.out.println((double) this.workWithCollection.add(this.arrayList,  this.NUMBERLINES) / NUMBERFORCONVERTNANO);
        System.out.println((double) this.workWithCollection.add(this.linkedList,  this.NUMBERLINES) / NUMBERFORCONVERTNANO);
        System.out.println((double) this.workWithCollection.add(this.treeSet,  this.NUMBERLINES) / NUMBERFORCONVERTNANO);

    }


    /**
     * Method for test delete strings to collection.
     */
    public void testDelete() {

        System.out.println(this.workWithCollection.delete(this.arrayList,  this.NUMBERLINES));
        System.out.println(this.workWithCollection.delete(this.linkedList,  this.NUMBERLINES));
        System.out.println(this.workWithCollection.delete(this.treeSet,  this.NUMBERLINES));

    }

    /**
     * Method add string array to list.
     */
    public void testAddArrayToList() {
        CreateRandomString createRandomString = new CreateRandomString();
        final int arraySize = 1000000;

        String[] array = new String[arraySize];

        Random rn = new Random();

        final int lengthString = 100;

        for (int i = 0; i < arraySize; i++) {
            int stringLength = rn.nextInt(lengthString);

            String tmpString = createRandomString.createRandomString(stringLength);

            array[i] = tmpString;
        }
        System.out.println((double) this.workWithCollection.add(this.arrayList,  array) / NUMBERFORCONVERTNANO);
        System.out.println((double) this.workWithCollection.add(this.linkedList,  array) / NUMBERFORCONVERTNANO);
        System.out.println((double) this.workWithCollection.add(this.treeSet,  array) / NUMBERFORCONVERTNANO);

    }

}
