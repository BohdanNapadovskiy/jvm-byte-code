package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;
import ru.napadovskiu.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
public class UserServlet extends HttpServlet {

    /**
     *
     */
    private final UserStore usersStore = UserStore.getInstance();

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
            User userAdmin = UserStore.getInstance().selectUser(login, password);
            if (userAdmin.getRole().getRoleName().equals("superAdmin")) {
                session.setAttribute("userRole", "superAdmin");
            }
            req.setAttribute("users", UserStore.getInstance().selectAllUser());
            req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/views/loginView.jsp").forward(req, resp);
        }
    }

}
