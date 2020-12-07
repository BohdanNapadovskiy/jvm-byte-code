package ru.napadovskiu.users;


import java.sql.Timestamp;

public class User {

    /**
     *Name user.
     */
    private String name;

    /**
     *Login user.
     */
    private String login;

    /**
     *Email user.
     */
    private String email;

    /**
     *Date of crate user.
     */
    private Timestamp createDate;

    /**
     * Constructor for class.
     * @param name name user.
     * @param login login user.
     * @param email email user.
     * @param createDate date of crate user.
     */
    public User(String name, String login, String email, Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return this.login;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @return
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

}
