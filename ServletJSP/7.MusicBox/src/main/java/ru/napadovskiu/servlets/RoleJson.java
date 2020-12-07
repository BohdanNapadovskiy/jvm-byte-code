package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import ru.napadovskiu.entities.Role;
import ru.napadovskiu.store.RoleStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoleJson extends HttpServlet {

    private final RoleStore roleStore = RoleStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Role> roles = roleStore.getAll();
        String json = new Gson().toJson(roles);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }


}
