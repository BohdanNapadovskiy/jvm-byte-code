package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;
import ru.napadovskiu.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class EditUserServlets extends HttpServlet {

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
        resp.setContentType("text/html");
        int userId = Integer.parseInt(req.getParameter("userId"));
        User editUser = usersStore.selectUser(userId);
        req.setAttribute("editUser", editUser);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editUser.jsp");
        rd.forward(req, resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        String oldEmail = req.getParameter("oldEmail");
        String oldName = req.getParameter("oldName");
        String oldLogin = req.getParameter("oldLogin");

        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("newPassword");
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        boolean result = this.usersStore.updateUser(oldName, oldLogin, oldEmail, name, login, email, password, country, city);
        if (result) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            resp.sendError(404);
        }


    }

}
