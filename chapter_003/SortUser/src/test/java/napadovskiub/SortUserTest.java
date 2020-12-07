package napadovskiub;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Napadovskiy Bohdan on 08.06.2017.
 */
public class SortUserTest {

    /**
     *
     */
    @Test
    public void sortTest() {

        SortUser sortUser = new SortUser();
        final int firstAge = 25;
        final int secondAge = 20;
        final int firstId = 1;
        final int secondId = 2;
        User firstUser = new User(firstId, firstAge, "Иван");
        User secondUser = new User(secondId, secondAge, "Петр");

        ArrayList<User> list = new ArrayList<User>();

        list.add(firstUser);
        list.add(secondUser);

        Set<User> treeSet = sortUser.sort(list);

        assertThat(treeSet.toArray()[0], is(secondUser));

    }

    /**
     *
     */
    @Test
    public void whenSortByLengthNameThenReturnSmallestLength() {
        SortUser sortUser = new SortUser();
        final int firstAge = 25;
        final int secondAge = 20;
        final int firstId = 1;
        final int secondId = 2;

        User thirdUser = new User(secondId, secondAge, "Петрович");
        User secondUser = new User(secondId, secondAge, "Петр");
        User firstUser = new User(firstId, firstAge, "Иваныч");

        ArrayList<User> list = new ArrayList<User>();

        list.add(firstUser);
        list.add(secondUser);
        list.add(thirdUser);

        List<User> treeSet = sortUser.sortNameLength(list);

        assertThat(treeSet.toArray()[0], is(secondUser));
    }

    /**
     *
     */
    @Test
    public void whenSortByAllFieldsThenReturnList() {
        SortUser sortUser = new SortUser();
        final int firstAge = 25;
        final int secondAge = 20;
        final int thirdAge  = 30;
        final int firstId = 1;
        final int secondId = 2;

        User firstUser = new User(firstAge, firstAge, "Сергей");
        User secondUser = new User(thirdAge, thirdAge, "Иван");
        User thirdUser = new User(firstId, secondAge, "Сергей");
        User fourthUser = new User(secondId, firstAge, "Иван");


        ArrayList<User> list = new ArrayList<User>();

        list.add(firstUser);
        list.add(secondUser);
        list.add(thirdUser);
        list.add(fourthUser);

        List<User> treeSet = sortUser.sortByAllFields(list);

        assertThat(treeSet.toArray()[0], is(fourthUser));
    }


}