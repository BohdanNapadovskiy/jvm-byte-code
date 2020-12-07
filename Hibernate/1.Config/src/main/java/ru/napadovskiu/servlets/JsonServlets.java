package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import ru.napadovskiu.models.Items;
import ru.napadovskiu.storage.HBStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *
 */
public class JsonServlets extends HttpServlet {

    /**
     *
     */
    private HBStorage store = HBStorage.getInstance();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Items> items = (List<Items>) store.getAll();
        String json = new Gson().toJson(items);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}
