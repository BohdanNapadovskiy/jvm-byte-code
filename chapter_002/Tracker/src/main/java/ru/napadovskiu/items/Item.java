package ru.napadovskiu.items;

import java.util.ArrayList;
import java.util.Date;

/**
 //*Class Item.
 *@author napadovskiy
 *@since 13.09.2016
 *@version 1
*/

public class Item {
    /**
     *Id item.
     */
    private String id;

    /**
     * Name item.
     */
    private String name;

    /**
     *Item date.
     */
    private long createDate;

    /**
     * Item description.
     */
    private String description;

    /**
     *
     */
    private final ArrayList<Comments> comments = new ArrayList<>();
    /**
     *
     */
    private Integer commentPosition = 0;

    /**
     *
     */
    public Item() {

    }

    /**
     * Constructor for class Item.
     * @param name Item name.
     * @param desc Item description.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.description = desc;
	}

    /**
     *Method to set id Item.
     * @param id id Item.
     */
	public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to set date for Item.
     * @param createDate Item date.
     */
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    /**
     * Method set name of Item.
     * @param name item name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Method set description for Item.
     * @param description Item description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method set Item comment.
     * @param comment Item comment.
     */
    public void setComment(Comments comment) {
        this.comments.set(this.commentPosition, comment);

    }

    /**
     * Method get Item id.
     * @return item id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method return Item date.
     * @return Item date.
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Method return item description.
     * @return item description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method return item name.
     * @return item name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method add comments to item.
     * @param comment item comment.
     * @return item comment.
     */
    public Comments addComment(Comments comment) {
        //comments[this.commentPosition++] = comment;
        this.comments.add(comment);
        return comment;
    }

    /**
     * Method return comment item.
     * @return item comment.
     */
    public String getComment() {
        String result = "";
//        for (int i = 0; i < comments.size(); i++) {
//            if (this.comments[i] != null) {
//                result = comments[i].getComment();
//            }
//        }

        for (Comments comment: this.comments) {
            result = comment.getComment();
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        Date date = new Date(this.createDate);
		return "id: " + this.id + ";   name: " + this.name + ";   description: " + this.description + ";   comments: " + this.getComment() + "; date: " + date;
    }
}