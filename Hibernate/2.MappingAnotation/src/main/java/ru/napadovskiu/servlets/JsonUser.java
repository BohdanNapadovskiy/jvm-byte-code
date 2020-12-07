package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.napadovskiu.entities.*;
import ru.napadovskiu.services.CarSerializer;
import ru.napadovskiu.services.ItemSerializer;
import ru.napadovskiu.services.UserSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Gson gson = new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).create();
        String json = gson.toJson(user);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }
}
