package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;
import ru.napadovskiu.users.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRoleServlets extends HttpServlet {

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
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editRole.jsp");
        rd.forward(req, resp);
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
        resp.setContentType("text/html");

        String email = req.getParameter("email");
        User user = this.usersStore.selectUser(email);
        String roleName = req.getParameter("role");


        boolean result = this.usersStore.updateRole(user.getId(), roleName);
        if (result) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }


}
