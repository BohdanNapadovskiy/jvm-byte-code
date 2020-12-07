package ru.napadovskiy.userstorage;

/**
 * Package of Multithreading treads.
 * Class counter.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 04.09.2017
 */
public class User {

    /**
     * id User.
     */
    private int idUser;

    /**
     * amount.
     */
    private int amount;

    /**
     * Constructor for class with param.
     * @param idUser id user.
     * @param amount amount.
     */
    public User(int idUser, int amount) {
        this.idUser = idUser;
        this.amount = amount;
    }

    /**
     * Method return id user.
     * @return ud user.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Method return amount.
     * @return user amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Method set amount to user.
     * @param amount amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
