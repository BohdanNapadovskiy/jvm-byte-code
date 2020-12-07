package ru.napadovskiu.entities;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "users")
public class User {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;

    /**
     *
     */
    @Column(name = "user_name")
    private String userName;

    /**
     *
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param userId
     * @param userName
     * @param userPassword
     */
    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }


    /**
     *
     * @param userId
     */
    public User(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
