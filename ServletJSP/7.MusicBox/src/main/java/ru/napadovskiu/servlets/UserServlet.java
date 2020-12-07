package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import ru.napadovskiu.entities.MusicType;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class UserServlet extends HttpServlet {

    /**
     *
     */
    private final UserStore usersStore = UserStore.INSTANCE;

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
            User userAdmin = usersStore.selectUser(login, password);
            List<MusicType> musicType =  userAdmin.getMusicType();
            if (userAdmin.getRole().getUser_role().equals("Admin")) {
                session.setAttribute("userRole", "Admin");
            }
            req.getRequestDispatcher("/WEB-INF/views/usersView.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/views/loginView.jsp").forward(req, resp);
        }
    }

}
