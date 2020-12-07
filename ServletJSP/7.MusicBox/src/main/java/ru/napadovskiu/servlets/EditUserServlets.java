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
public class EditUserServlets extends HttpServlet {

    /**
     *
     */
    private final UserStore usersStore = UserStore.INSTANCE;

    private final AddressStore addressStore = AddressStore.INSTANCE;

    private final RoleStore roleStore = RoleStore.INSTANCE;

    private final MusicStore musicStore = MusicStore.INSTANCE;



    private List<MusicType> takeListMusicType(String[] arrayTypeMusic) {
        List<MusicType> musicTypeList = new CopyOnWriteArrayList<>();
        if (arrayTypeMusic !=null && arrayTypeMusic.length !=0) {
            for (String typeMusic: arrayTypeMusic) {
                MusicType musicType = musicStore.getByName(typeMusic);
                musicTypeList.add(musicType);
            }
        }

        return musicTypeList;
    }

    private void addMusicTypeListToUser(User user, List<MusicType> list) {
        for (MusicType musicType: list) {
            if (!usersStore.isUserHaveMusicType(user, musicType)) {
                musicStore.addMusicToUser(user, musicType);
            }
        }
    }
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
        int userId = Integer.parseInt(req.getParameter("userId"));
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        User editUser = usersStore.getById(userId);
        Role role = roleStore.getById(roleId);
        req.setAttribute("editUser", editUser);
        req.setAttribute("role", role.getUser_role());
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
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
        int userId = Integer.parseInt(req.getParameter("userId"));
        User editUser = usersStore.getById(userId);

        editUser.setName(req.getParameter("name"));
        editUser.setPassword(req.getParameter("password"));
        editUser.setLogin(req.getParameter("login"));

        Role role = roleStore.getByName(req.getParameter("role"));
        editUser.setRole(role);
        usersStore.updateRoleInUser(editUser, role);

        Address editAddress = addressStore.getByName(req.getParameter("address"));
        if (editAddress == null) {
            editAddress = new Address(req.getParameter("address"));
            if (addressStore.create(editAddress)) {
                editAddress = addressStore.getByName(editAddress.getAddress_name());
            }
        }
        usersStore.updateAddressInUser(editUser,editAddress);
        editUser.setAddress(editAddress);


        List<MusicType> musicTypes =  takeListMusicType(req.getParameterValues("music"));
        if (musicTypes.size() != 0 ) {
            addMusicTypeListToUser(editUser,musicTypes);
        }

        req.getRequestDispatcher("/WEB-INF/views/usersView.jsp").forward(req, resp);
    }

}
