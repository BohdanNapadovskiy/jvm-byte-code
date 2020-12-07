package ru.napadovskiu.servlets;



import ru.napadovskiu.entities.Car;
import ru.napadovskiu.entities.Engine;
import ru.napadovskiu.entities.Item;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.services.CreateItem;
import ru.napadovskiu.storage.CarStorage;
import ru.napadovskiu.storage.ItemStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import ru.napadovskiu.storage.EngineStorage;

/**
 *
 */
public class EditItemServlets extends HttpServlet {

    /**
     *
     */
    private final ItemStorage itemStorage = ItemStorage.getInstance();

    /**
     *
     */
    private final EngineStorage engineStorage = EngineStorage.getInstance();

    /**
     *
     */
    private CarStorage carStorage = CarStorage.getInstance();

    /**
     *
     */
    private final CreateItem createItem = CreateItem.getInstance();


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
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        Item editItem = itemStorage.get(itemId);
        req.setAttribute("editItem", editItem);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editItem.jsp");
        rd.forward(req, resp);
    }


    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setContentType("text/html");
        int editItemId = Integer.parseInt(req.getParameter("itemId"));
        int carEditId = Integer.parseInt(req.getParameter("carId"));

        HttpSession session = req.getSession();

        User itemUser = (User) session.getAttribute("user");

        Item editItem = itemStorage.get(editItemId);
        Car editCar = carStorage.get(carEditId);
        String carName = req.getParameter("car_name");
        String gearBox = req.getParameter("select_GearBox");
        String transm = req.getParameter("select_Transmission");
        String engine = req.getParameter("select_engine");

        createItem.editItem(editItem, editCar, carName, gearBox, transm, engine,itemUser);

        List<Item> items = itemStorage.getAll();
        req.setAttribute("advertisements", items);

        req.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(req, resp);
   }

}
