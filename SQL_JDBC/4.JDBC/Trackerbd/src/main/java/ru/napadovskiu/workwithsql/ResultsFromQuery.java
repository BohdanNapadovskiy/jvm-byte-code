package ru.napadovskiu.workwithsql;

import ru.napadovskiu.items.Comments;
import ru.napadovskiu.items.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Class for work with result of query.
 */
public class ResultsFromQuery {

    /**
     * Method return item from result of SQL Query.
     * @param resultQuery result of Query
     * @return result Item.
     * @throws SQLException exception.
     */
    public Item getItemFromResultQuery(ResultSet resultQuery) throws SQLException {
        Item result = null;
        while (resultQuery.next()) {
            Item newItem = new Item();
            newItem.setId(resultQuery.getString("item_id"));
            newItem.setName(resultQuery.getString("item_name"));
            newItem.setDescription(resultQuery.getString("item_desc"));
            newItem.setDescription(resultQuery.getString("item_date"));
            result = newItem;
        }
        return result;
    }

    /**
     *  Method return arrays of items from result of Query.
     * @param resultQuery result of Query
     * @return result array of Items.
     * @throws SQLException exception.
     */
    public ArrayList<Item> getItemsFromResultQuery(ResultSet resultQuery) throws SQLException {
        ArrayList<Item> result = new ArrayList<Item>();
        while (resultQuery.next()) {
            Item newItem = new Item();
            newItem.setId(resultQuery.getString("item_id"));
            newItem.setName(resultQuery.getString("item_name"));
            newItem.setDescription(resultQuery.getString("item_desc"));
            newItem.setCreateDate(resultQuery.getTimestamp("item_date").getTime());
            result.add(newItem);
        }
        return result;
    }

    /**
     *  Method return arrays of comments from result of Query.
     * @param resultQuery result of Query
     * @return result array of Comments.
     * @throws SQLException exception.
     */
    public ArrayList<Comments> getCommentsFromResultQuery(ResultSet resultQuery) throws SQLException {
        ArrayList<Comments> result = new ArrayList<Comments>();
        while (resultQuery.next()) {
            Comments comments = new Comments();
            comments.setComment(resultQuery.getString("comments_description"));
            result.add(comments);
        }
        return result;
    }

}
