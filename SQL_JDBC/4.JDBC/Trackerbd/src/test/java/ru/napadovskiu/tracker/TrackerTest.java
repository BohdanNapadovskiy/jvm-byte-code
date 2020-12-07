package ru.napadovskiu.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.napadovskiu.items.Item;
import static org.hamcrest.core.Is.is;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertThat;



public class TrackerTest {

    private Tracker tracker = null;

    private Connection connection = null;


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

    @Test
    public void whenAddItemThenReturnItem() {
        Item firstItem = new Item("firstItemName", "FisrtItemDesc");
        Item addItem = this.tracker.addNewItem(firstItem);
        assertThat(addItem, is(firstItem));
    }

    @Test
    public void whenDeleteItemThenReturnTrue() {
        Item tmpItem = new Item("firstItemName", "tmpDescription");
        this.tracker.addNewItem(tmpItem);
        this.tracker.addNewItem(tmpItem);
        Boolean result = this.tracker.deleteItem(tmpItem);
        assertThat(result, is(true));
    }

    @Test
    public void whenFindByIdThenReturnItem() {
        Item firstItem = new Item("firstItemName", "FisrtItemDesc");
        Item secondItem = new Item("2ItemName", "2ItemDesc");
        this.tracker.addNewItem(firstItem);
        this.tracker.addNewItem(secondItem);
        Item findItem = this.tracker.findItemById(firstItem.getId());
        assertThat(findItem.getId(), is(firstItem.getId()));

    }

    @Test
    public void whenFindByNameThenReturnArrayOfItem() {
        Item tmpItem = new Item("firstItemName", "tmpDescription");
        this.tracker.addNewItem(tmpItem);

        ArrayList<Item> itemArrayList = tracker.findItemByName("firstItemName");
        assertThat(itemArrayList.isEmpty(), is(false));

    }

    @Test
    public void whenFindByDescriptionThenReturnArrayOfItem() {

        Item firstItem = new Item("firstItemName", "FisrtItemDesc");
        Item secondItem = new Item("2ItemName", "2ItemDesc");
        Item tmpItem = new Item("firstItemName", "tmpDescription");

        this.tracker.addNewItem(firstItem);
        this.tracker.addNewItem(secondItem);
        this.tracker.addNewItem(tmpItem);

        ArrayList<Item> itemArrayList = tracker.findItemByDescription("Item");
        assertThat(itemArrayList.isEmpty(), is(false));
    }

    @Test
    public void whenEditItemThenReturnNewItem() {
        Item firstItem = new Item("firstItemName", "FisrtItemDesc");
        Item editItem = new Item("newNameOfItem", "newDescriptionOfItem");

        this.tracker.addNewItem(firstItem);
        editItem.setId(firstItem.getId());

        this.tracker.editItem(editItem);
        Item findItem = this.tracker.findItemById(editItem.getId());
        assertThat(findItem.getName(), is("newNameOfItem"));

    }

    @Test
    public void whenGetAllItemThenReturnArrayOfAllItem() {
        Item firstItem = new Item("firstItemName", "FisrtItemDesc");
        Item secondItem = new Item("2ItemName", "2ItemDesc");
        this.tracker.addNewItem(firstItem);
        this.tracker.addNewItem(secondItem);
 
        ArrayList<Item> itemArrayList = tracker.findItemByName("firstItemName");
        assertThat(itemArrayList.isEmpty(), is(false));

    }
}