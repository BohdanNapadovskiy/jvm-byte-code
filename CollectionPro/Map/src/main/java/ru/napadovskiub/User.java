package ru.napadovskiub;

import java.util.Calendar;

/**
 * Class user.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 17.07.2017
 */
public class User {

    /**
     * Name user.
     */
    private String name;

    /**
     * number of children.
     */
    private int children;

    /**
     * date of birth day.
     */
    private Calendar birthday;

    /**
     * Constructor for class user.
     * @param name name user.
     * @param birthday date of birthday.
     */
    public User(String name, Calendar birthday) {
        this.name = name;
        this.birthday = birthday;
        this.children = 0;
    }

    /**
     * Constructor with parameters.
     * @param name name user.
     * @param birthday date of birthday.
     * @param children number of children.
     */
    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }


    /**
     * Method return user name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method return number of children.
     * @return children.
     */
    public int getChildren() {
        return children;
    }

    /**
     *Method return date of birthday.
     * @return date of birthday.
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     *Method set number of children.
     * @param children number of children.
     */
    public void setChildren(int children) {
        this.children = children;
    }
}
