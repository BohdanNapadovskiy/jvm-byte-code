package napadovskiub;

/**
 * Created by Napadovskiy Bohdan on 08.06.2017.
 */
public class User {

    /**
     * User id.
     */
    private int id;

    /**
     * User name.
     */
    private String name;

    /**
     * Constructor for User class.
     * @param id user id.
     * @param name user name.
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method return user name.
     * @return user name.
     */
    public String getName() {
        return this.name;
    }

    /**
     *Method return user id.
     * @return user id.
     */
    public int getId() {
        return this.id;
    }


}
