package ru.napadovskiu.servlets;

import ru.napadovskiu.entities.Item;
import ru.napadovskiu.storage.ItemStorage;
import ru.napadovskiu.storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class ItemServlet extends HttpServlet {

    /**
     *
     */
    private final UserStorage userStorage = UserStorage.getInstance();

    private final ItemStorage itemStorage = ItemStorage.getInstance();
    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("login") != null) {
            String login = (String) session.getAttribute("login");
            String password = (String) session.getAttribute("password");
            req.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/views/loginView.jsp").forward(req, resp);
        }
    }

}
