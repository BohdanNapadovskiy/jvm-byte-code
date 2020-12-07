package napadovskiub;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Napadovskiy Bohdan on 08.06.2017.
 */
public class UserConvertTest {


    /**
     * Method for test userConvert.
     */
    @Test
    public void whenConvertListToHashMapThenReturnMap() {
        final int firstId  = 1234;
        final int secondId = 1235;

        User firstUser = new User(firstId, "Pavel");
        User secondUser = new User(secondId, "Ivan");

        UserConvert userConvert = new UserConvert();

        ArrayList<User> userArrayList = new ArrayList<User>();

        userArrayList.add(firstUser);
        userArrayList.add(secondUser);

        HashMap<Integer, User> hashMap =  userConvert.process(userArrayList);

        HashMap<Integer, User> expected = new HashMap<Integer, User>();

        expected.put(firstId, firstUser);
        expected.put(secondId, secondUser);

        assertThat(hashMap, is(expected));

    }

}