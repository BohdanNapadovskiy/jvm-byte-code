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

import java.util.concurrent.CopyOnWriteArrayList;

public class UserServlet  extends HttpServlet {

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
        CopyOnWriteArrayList<User> arrayList  = usersStore.selectAllUser();
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
//        for (User user: arrayList) {
//            printWriter.append("user name :" + user.getName() + " user login: " + user.getLogin() + " user email: " + user.getEmail() + "\n");
//        }

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
        Timestamp dateOfCreate = new Timestamp(System.currentTimeMillis());
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), dateOfCreate);

        boolean result = this.usersStore.addUser(user);
        if (result) {
            responce = "User add successfully, user name: " + user.getName() + " user login: " + user.getLogin() + " user email " + user.getEmail();
        } else {
            responce = "It's not possible to add a user";
        }
        printWriter.write(responce);
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());

        String responce;
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        boolean result = this.usersStore.updateUser(name, login, email);
        if (result) {
            responce = "User update successfully";
        } else {
            responce = "It's not possible to add a user";
        }
        printWriter.write(responce);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String responce;
        String email = req.getParameter("email");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        boolean result = this.usersStore.deleteUser(email);
        if (result) {
             responce = "User delete successfully";
        } else {
            responce = "User with email " + email + " do not exist";
        }
        printWriter.write(responce);
        printWriter.flush();
    }
}
