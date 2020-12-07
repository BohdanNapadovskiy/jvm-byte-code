package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;

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
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Edit user</title>"
                + "</head>"
                + "<body>");
        printWriter.append("<br><form action='" + req.getContextPath() + "/edit' method='post'>\n"+
                "<table><tr>\n"+
                "<td>Name: <input type=text value='" + req.getParameter("name") + "' name='name'/></td>\n"+
                "<td>Login: <input type=text value='" + req.getParameter("login") + "' name='login'/></td>\n"+
                "<td>E-mail: <input type=text value='" + req.getParameter("email") + "' name='email'/></td>\n"+
                "<td><input type='submit' value='edit'></td></form>\n"+
                "</tr></table>\n"+
                "<br><form action='" + req.getContextPath() + "/list' method='get'>\n"+
                "<input type='submit' value='back'></form>\n"+
                "</body>\n"+
                "</html>");
        printWriter.flush();
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

        String responce;
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        boolean result = this.usersStore.updateUser(name, login, email);
        if (result) {
            resp.sendRedirect(req.getContextPath().concat("/list"));
        }
        printWriter.flush();

    }

}
