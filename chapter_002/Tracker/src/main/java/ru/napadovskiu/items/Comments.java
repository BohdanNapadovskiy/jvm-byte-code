package ru.napadovskiu.items;

/**
 * Created by program on 25.09.2016.
 */
public class Comments {

    /**
     * comment for Item.
     */
    private String comment;

    /**
     * Constructor with text.
     * @param text string for comments.
     */
    public Comments(String text) {
        this.comment = text;
    }

    /**
     * Default constructor.
     */
    public Comments() {

    }

    /**
     * Method return comment.
     * @return comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Method set comment.
     * @param comment comment for print.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Method for toString.
     * @return comment.
     */
    @Override
    public String toString() {
        return comment;
    }

}
