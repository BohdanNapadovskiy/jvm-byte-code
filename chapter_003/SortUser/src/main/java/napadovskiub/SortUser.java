package napadovskiub;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Napadovskiy Bohdan on 08.06.2017.
 */
public class SortUser {

    /**
     * Method return sorted TreeSet.
     * @param list with users for sorting.
     * @return treeset.
     */
    public Set<User> sort(List<User> list) {
        TreeSet result = new TreeSet();
        result.addAll(list);

        return  result;
    }



    /**
     * Method sort users by length name.
     * @param list list for sort
     * @return result list.
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, (user1, user2) -> user1.getName().length() - user2.getName().length());

        return list;

    }

    /**
     * Method sort users by name and age.
     * @param list list for sort.
     * @return result list.
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, (user1, user2) -> {
            int result;
            result = user1.getName().compareTo(user2.getName());
            if (result  == 0) {
                result = user1.getAge() - user2.getAge();
            }
            return result;
        });

       return list;

    }



}
