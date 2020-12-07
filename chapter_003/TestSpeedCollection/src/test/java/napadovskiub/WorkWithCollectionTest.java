package napadovskiub;

import org.junit.Test;


import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Napadovskiy on 30.05.2017.
 */
public class WorkWithCollectionTest {

    /**
     * Method check adds string to collection.
     */
    @Test
    public void whenAddStringThenCollectionNotEmpty() {


        final int amount = 1;

        ArrayList<String> arrayList = new ArrayList<String>();


        WorkWithCollection workWithCollection = new WorkWithCollection();

        workWithCollection.add(arrayList,  amount);


        assertThat(arrayList.isEmpty(), is(false));

    }

    /**
     *  Method check delete string from collection.
     */

    @Test
    public void whenDeleteStringThenCollectionEmpty()  {

        final int amount = 1;

        boolean result = true;

        ArrayList<String> arrayList = new ArrayList<String>();

        WorkWithCollection workWithCollection = new WorkWithCollection();


        workWithCollection.add(arrayList, amount);
        workWithCollection.delete(arrayList, amount);

        assertThat(arrayList.isEmpty(), is(result));

    }

}