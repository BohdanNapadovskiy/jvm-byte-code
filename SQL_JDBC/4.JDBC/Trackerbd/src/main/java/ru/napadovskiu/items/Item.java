package ru.napadovskiu.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.workwithsql.ResultsFromQuery;
import ru.napadovskiu.workwithsql.Settings;
import ru.napadovskiu.workwithsql.WorkWithSQL;

import java.io.InputStream;
import java.sql.*;
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
    private final Settings settings = new Settings();

    /**
     *
     */
    public Item() {
        initSettings();
    }

    private static final Logger LOG = LoggerFactory.getLogger(WorkWithSQL.class);

    /**
     * Constructor for class Item.
     * @param name Item name.
     * @param desc Item description.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.description = desc;
        initSettings();
	}

    public Item(String id, String name, String desc, long createDate) {
        this.setId(id);
        this.name = name;
        this.description = desc;
        this.createDate = createDate;
        initSettings();
    }


    private  void initSettings() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
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
        try (Connection connection = DriverManager.getConnection(this.settings.getValue("url"), this.settings.getValue("userName"), this.settings.getValue("password"));
             PreparedStatement pst = connection.prepareStatement("INSERT INTO table_comments (comments_description, item_id) VALUES(?,?)");) {
            pst.setString(1, comment.getComment());
            pst.setString(2, this.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return comment;
    }

    /**
     * Method return comment item.
     * @return item comment.
     */
    public ArrayList<Comments> getComment() {
        ArrayList<Comments> resultArray = new ArrayList<Comments>();
        try (Connection connection = DriverManager.getConnection(this.settings.getValue("url"), this.settings.getValue("userName"), this.settings.getValue("password"));
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM table_comments WHERE item_id =? ");) {
            pst.setString(1, this.getId());
            ResultSet resultQuery =  pst.executeQuery();
            ResultsFromQuery resultsFromQuery = new ResultsFromQuery();
            resultArray = resultsFromQuery.getCommentsFromResultQuery(resultQuery);
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return resultArray;
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