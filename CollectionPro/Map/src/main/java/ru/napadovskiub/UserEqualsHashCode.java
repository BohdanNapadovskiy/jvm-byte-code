package ru.napadovskiub;

import java.util.Calendar;

/**
 * Class user.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 17.07.2017
 */
public class UserEqualsHashCode extends User {

    /**
     * Constructor for class user.
     *
     * @param name     name user.
     * @param birthday date of birthday.
     */
    public UserEqualsHashCode(String name, Calendar birthday) {
        super(name, birthday);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        final int var = 17;
        int result = var;
        result = prime * result + getName().hashCode();
        result = prime * result + getBirthday().hashCode();
        result = prime * result + getChildren();
        return result;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;

        if (!getName().equals(user.getName())) {
            return false;
        }
        return getBirthday().equals(user.getBirthday());
    }
}
