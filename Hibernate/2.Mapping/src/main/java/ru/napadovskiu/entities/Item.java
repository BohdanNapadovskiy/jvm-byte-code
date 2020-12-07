package ru.napadovskiu.entities;

import java.sql.Timestamp;

/**
 *
 */
public class Item {

    /**
     *
     */
    private int itemId;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private Car car;

    /**
     *
     */
    private boolean closed;

    /**
     *
     */
    private User user;

    /**
     *
     */
    private Timestamp date;

    /**
     *
     */
    public Item() {
    }

    /**
     *
     * @param itemId
     */
    public Item(int itemId) {
        this.itemId = itemId;
    }

    /**
     *
     * @return
     */
    public int getItemId() {
        return itemId;
    }

    /**
     *
     * @param itemId
     * @param description
     * @param car
     * @param closed
     * @param user
     * @param date
     */
    public Item(int itemId, String description, Car car, boolean closed, User user, Timestamp date) {
        this.itemId = itemId;
        this.description = description;
        this.car = car;
        this.closed = closed;
        this.user = user;
        this.date = date;
    }

    /**
     *
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     *
     * @return
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     *
     * @param closed
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

}
