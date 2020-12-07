package ru.napadovskiu.servlets;

import ru.napadovskiu.store.UserStore;
import ru.napadovskiu.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

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
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder();
        CopyOnWriteArrayList<User> listOfUsers = usersStore.selectAllUser();

        for (User user : listOfUsers) {
            sb.append("<tr>\n");
            sb.append("<td>"+user.getName()+"</td>\n");
            sb.append("<td>"+user.getLogin()+"</td>\n");
            sb.append("<td>"+user.getEmail()+"</td>\n");
            sb.append("<td>"+user.getCreateDate()+"</td>\n");
            sb.append("<td><form action='"+req.getContextPath()+"/create' method='get'>\n"+
                    "<input type='submit' value='Add new user'> </form></td>\n"+
                    "<td><form action='"+req.getContextPath()+"/edit' method='get'>\n"+
                    "<input type='submit' value='edit'>\n"+
                    "<input type='hidden' name='name' value='" + user.getName() + "'/>\n"+
                    "<input type='hidden' name='login' value='" + user.getLogin() + "'/>\n"+
                    "<input type='hidden' name='email' value='" + user.getEmail() + "'/></form></td>\n"+
            "</tr>\n");
        }

        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 500px\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>User name</td>\n" +
                "        <td>Login</td>\n" +
                "        <td>email</td>\n" +
                "        <td>create date</td>\n" +
                "        <td></td>\n" +
                "        <td></td>\n" +
                "    </tr>\n" +
                sb.toString() +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
        printWriter.flush();

    }

}
