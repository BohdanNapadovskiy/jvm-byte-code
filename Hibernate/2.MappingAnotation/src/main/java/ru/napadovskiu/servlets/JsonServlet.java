package ru.napadovskiu.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.napadovskiu.entities.*;
import ru.napadovskiu.services.CarSerializer;
import ru.napadovskiu.services.ItemSerializer;
import ru.napadovskiu.services.UserSerializer;
import ru.napadovskiu.storage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Stack;


public class JsonServlet extends HttpServlet {

    /**
     *
     */
    private final EngineStorage engineStorage = EngineStorage.getInstance();

    private final GearBoxStorage gearBoxStorage = GearBoxStorage.getInstance();

    private final TransStorage transStorage = TransStorage.getInstance();

    private final ItemStorage itemStorage = ItemStorage.getInstance();

    private final CarStorage carStorage = CarStorage.getInstance();

    private String takeCarTable(String queryName, HttpServletRequest req) {
        String carName = req.getParameter("nameFilter");

        String json = null;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Item.class, new ItemSerializer())
                .registerTypeAdapter(Car.class, new CarSerializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .create();

        if (queryName.equals("allTable")) {
            json = gson.toJson(itemStorage.getAll());
        } else if(queryName.equals("By car")) {
            json = gson.toJson(itemStorage.getByCarType(carName));
        } else if(queryName.equals("With photo")) {
            json = gson.toJson(itemStorage.getAllByPhoto());
        } else {
            json = gson.toJson(itemStorage.getAllByDate());
        }
        return json;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        String queryName = req.getParameter("namePart");

        String json = null;
        if (queryName.equals("engine") ) {
            List<Engine> list = engineStorage.getAll();
            json = new Gson().toJson(list);
        } else if(queryName.equals("gearBox")) {
            List<GearBox> list = gearBoxStorage.getAll();
            json = new Gson().toJson(list);

        } else if (queryName.equals("allTable") || queryName.equals("By car") ) {
            json = takeCarTable(queryName, req);

        } else if (queryName.equals("allBodyCar")) {
            List<Car> list = carStorage.getAllCarName();
            json = new Gson().toJson(list);
        } else if (queryName.equals("With photo")) {
            json = takeCarTable(queryName, req);
        } else if (queryName.equals("Current date"))  {
            json = takeCarTable(queryName, req);
        } else {
            List<Transmission> list = transStorage.getAll();
            json = new Gson().toJson(list);

        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}
