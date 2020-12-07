package napadovskiub;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Created by Napadovskiy Bohdan on 08.06.2017.
 */
public class UserConvert {

    /**
     * Method to convert ArrayList to HashMap.
     * @param list with users.
     * @return Map with users.
     */

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> resultMap = new HashMap<Integer, User>();

        for (User user : list) {
            resultMap.put(user.getId(), user);
        }


        return resultMap;
    }


}
