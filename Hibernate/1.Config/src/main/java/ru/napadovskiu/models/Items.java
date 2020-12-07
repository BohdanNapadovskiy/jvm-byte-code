package ru.napadovskiu.models;

import java.sql.Timestamp;

/**
 *Class Items.
 *@author napadovskiy
 *@since 12.08.2017
 *@version 1
 */
public class Items {

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String desc;

    /**
     *
     */
    private Timestamp created;

    /**
     *
     */
    private boolean done;

    /**
     * Constructor with param.
     * @param desc description item.
     * @param date item date.
     */
    public Items(String desc, Timestamp date) {
        this.desc = desc;
        this.created = date;
    }

    /**
     *Default constructor
     */
    public Items() {

    }

    /**
     * method return id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * method set id in item.
     * @param  id of item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method return description.
     * @return description of item.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Method set description in item.
     * @param desc description of item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Method return date of item.
     * @return date of create.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Method set date of create for item.
     * @param created createDate for item.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Method return flag is done.
     * @return flag done item.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Method set done fro item.
     * @param done flag done for item.
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
