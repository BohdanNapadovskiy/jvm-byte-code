package ru.napadovskiu.servlets;


import ru.napadovskiu.entities.Address;
import ru.napadovskiu.entities.MusicType;
import ru.napadovskiu.entities.Role;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.store.AddressStore;
import ru.napadovskiu.store.MusicStore;
import ru.napadovskiu.store.RoleStore;
import ru.napadovskiu.store.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class CreateUserServlet extends HttpServlet {

    /**
     *
     */
    private final UserStore usersStore = UserStore.INSTANCE;

    private final AddressStore addressStore = AddressStore.INSTANCE;

    private final RoleStore roleStore = RoleStore.INSTANCE;

    private final MusicStore musicStore = MusicStore.INSTANCE;


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
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/createUser.jsp");
        rd.forward(req, resp);
    }


    private void addMusicTypeListToUser(User user, List<MusicType> list) {
        for (MusicType musicType: list) {
            if (!usersStore.isUserHaveMusicType(user, musicType)) {
                musicStore.addMusicToUser(user, musicType);
            }
        }
    }

    private List<MusicType> takeListMusicType(String[] arrayTypeMusic) {
        List<MusicType> musicTypeList = new CopyOnWriteArrayList<>();
        for (String typeMusic: arrayTypeMusic) {
            MusicType musicType = musicStore.getByName(typeMusic);
            musicTypeList.add(musicType);
        }

        return musicTypeList;
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
        Boolean result = true;
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        if (usersStore.selectUser(login,name) != null) {
            result=false;
        } else {
            User user = new User(name, login);
            user.setPassword(password);


            if (usersStore.create(user)) {
                User createUser = usersStore.selectUser(user.getLogin(), user.getName());
                Address createAddress = new Address(req.getParameter("address"));
                if (addressStore.create(createAddress)) {
                    usersStore.updateAddressInUser(createUser,addressStore.getByName(createAddress.getAddress_name()));
                    createUser.setAddress(createAddress);
                }

                Role role = roleStore.getByName(req.getParameter("role"));
                usersStore.updateRoleInUser(createUser,roleStore.getByName(role.getUser_role()));

                List<MusicType> musicTypes =  takeListMusicType(req.getParameterValues("music"));


                createUser.setRole(role);

                addMusicTypeListToUser(createUser,musicTypes);
            }
        }

        if (result) {
            req.getRequestDispatcher("/WEB-INF/views/usersView.jsp").forward(req, resp);
        } else {

        }
   }
}
