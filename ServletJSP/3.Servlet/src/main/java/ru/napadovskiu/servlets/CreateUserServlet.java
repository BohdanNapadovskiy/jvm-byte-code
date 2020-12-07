package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;
import ru.napadovskiu.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 *
 */
public class CreateUserServlet extends HttpServlet {

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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Edit user</title>"
                + "</head>"
                + "<body>");
        writer.append("<br><form action='" + req.getContextPath() + "/create' method='post'>");
        writer.append("<table><tr>");
        writer.append("<td>Name: <input type=text name='name'/></td>");
        writer.append("<td>Login: <input type=text name='login'/></td>");
        writer.append("<td>E-mail: <input type=text name='email'/></td>");
        writer.append("<td><input type='submit' value='add'></td></form>");
        writer.append("</tr></table>");
        writer.append("<br><form Name ='back' action='" + req.getContextPath() + "/list' method='get'>");
        writer.append("<input type='submit' value='back'></form>");
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
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
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());

        Timestamp dateOfCreate = new Timestamp(System.currentTimeMillis());

        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), dateOfCreate);
        boolean result = this.usersStore.addUser(user);

        if (result) {
            resp.sendRedirect(req.getContextPath().concat("/list"));
        }
        printWriter.flush();

    }


}
