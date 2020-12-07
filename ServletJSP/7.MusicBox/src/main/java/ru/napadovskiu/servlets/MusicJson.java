package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import ru.napadovskiu.entities.MusicType;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.store.MusicStore;
import ru.napadovskiu.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class MusicJson extends HttpServlet {

    /**
     *
     */
    private final MusicStore musicStore = MusicStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<MusicType> listMusicType = musicStore.getAll();
        String json = new Gson().toJson(listMusicType);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        int userId = Integer.parseInt(req.getParameter("userId"));

        List<MusicType> listMusicType = musicStore.getAllMusicTypeByUser(userId);
        String json = new Gson().toJson(listMusicType);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }
}
