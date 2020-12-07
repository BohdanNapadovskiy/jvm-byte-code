package ru.napadovskiub.clientbank;

/**
 * @author Napadovskiy Bohdan.
 */

public class User {

    /**
     * user name.
     */
    private String name;

    /**
     * user passport.
     */
    private String passport;

    /**
     *Constructor for user class.
     * @param name user name.
     * @param passport user passport.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }


    /**
     * Method return user passport.
     * @return user passport.
     */
    public String getPassport() {
        return this.passport;
    }

    /**
     * Method return user name.
     * @return user name.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        return passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        final int var = 32;
        int result = name.hashCode();
        result = (var - 1) * result + passport.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", passport='" + passport + '\'' + '}';
    }
}
