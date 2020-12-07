package ru.napadovskiu.items;

import org.junit.Test;

import org.junit.Before;

import ru.napadovskiu.tracker.Tracker;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 */
public class ItemTest {

    /**
     *
     */
    private Tracker tracker = null;

    /**
     *
     */
    private Connection connection = null;

    /**
     *
     */
    @Before
    public void initialize() {
        Properties properties = new Properties();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", "postgres", "12341234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.tracker = new Tracker();

    }

    /**
     *
     */
    @Test
    public void whenAddCommentToItemThen() {
        Item firstItem = new Item("firstItem", "firstItemDescription");
        this.tracker.addNewItem(firstItem);
        Comments comments = new Comments("Comment to first item");
        Comments adComment = firstItem.addComment(comments);
        assertThat(adComment, is(comments));
    }

    @Test
    public void whenGetCommentThenReturnArrayOfComment() {
        Item firstItem = new Item("firstItem", "firstItemDescription");
        this.tracker.addNewItem(firstItem);
        Comments comments = new Comments("Comment to first item");
        firstItem.addComment(comments);
        ArrayList arrayOfComment = firstItem.getComment();
        assertThat(arrayOfComment.isEmpty(), is(false));




    }
}