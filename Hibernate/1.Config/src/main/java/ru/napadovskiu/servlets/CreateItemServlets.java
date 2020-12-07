package ru.napadovskiu.servlets;

import ru.napadovskiu.models.Items;
import ru.napadovskiu.storage.HBStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *Class create item servlets.
 *@author napadovskiy
 *@since 12.08.2017
 *@version 1
 */
public class CreateItemServlets extends HttpServlet {

    /**
     * Hibernate storage.
     */
    private HBStorage store = HBStorage.getInstance();

    /**
     * Method return timestamp from string
     * @param stringDate date in string.
     * @return result.
     */
    private Timestamp getTimestampFromHTML(String stringDate) {
        Timestamp result = null;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        try {
            Date docDate = format.parse(stringDate);
            result = new java.sql.Timestamp(docDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int result = 0;
        String description = req.getParameter("description");
        Timestamp createDate = getTimestampFromHTML(req.getParameter("createDate"));
        Items newItem = new Items(description, createDate);
        if (store.add(newItem) != 0) {
            req.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(req, resp);
        }

    }
}
