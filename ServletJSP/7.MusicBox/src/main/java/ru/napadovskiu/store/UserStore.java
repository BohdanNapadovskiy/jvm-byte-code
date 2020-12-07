package ru.napadovskiu.store;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.entities.Address;
import ru.napadovskiu.entities.MusicType;
import ru.napadovskiu.entities.Role;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.settings.Settings;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


public class UserStore implements AbstractStore<User> {


    /**
     *
     */
    private final Settings selectQuery = new Settings();


    /**
     *
     */
    private final Settings insertQuery = new Settings();

    /**
     *
     */
    private final Settings deleteQuery = new Settings();

    /**
     *
     */
    private final Settings updateQuery = new Settings();

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);


    public static final UserStore INSTANCE = new UserStore();

    /**
     *
     */
    private BasicDataSource ds;



    public UserStore() {
        ClassLoader loader = Settings.class.getClassLoader();
        this.selectQuery.load(loader.getResourceAsStream("select.properties"));
        this.insertQuery.load(loader.getResourceAsStream("insert.properties"));
        this.deleteQuery.load(loader.getResourceAsStream("delete.properties"));
        this.updateQuery.load(loader.getResourceAsStream("update.properties"));
    }


    @Override
    public User getById(int userId) {
        User user = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectUser")))  {
            pst.setInt(1, userId);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = new User(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        User user = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectUserByName")))  {
            pst.setString(1, name);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = new User(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public User selectUser(String login, String name) {
        User user = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectUserByLoginAndName")))  {
            pst.setString(1, login);
            pst.setString(2, name);

            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = new User(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }



    @Override
    public List<User> getAll() {
        List<User> result = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAllUser"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(new User(resultQuery));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean create(User user) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.insertQuery.getValue("insertUser"))) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getPassword());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean update(User user) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateUser"))) {
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getName());
            pst.setString(3, user.getPassword());
            pst.setInt(4, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.deleteQuery.getValue("deleteUser"));)  {
            pst.setInt(1, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public boolean updateAddressInUser(User user, Address address) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateAddressInUser"))) {
            pst.setInt(1, address.getAddress_id());
            pst.setInt(2, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public boolean updateRoleInUser(User user, Role role) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateRoleInUser"))) {
            pst.setInt(1, role.getRole_id());
            pst.setInt(2, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public boolean InsertMusicToUser(User user, MusicType musicType) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateRoleInUser"))) {
            pst.setInt(1, user.getId());
            pst.setInt(2, musicType.getMusic_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public boolean DeleteMusicFromUser(User user, MusicType musicType) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.deleteQuery.getValue("deleteMusicFromUser"))) {
            pst.setInt(1, musicType.getMusic_id());
            pst.setInt(2, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }



    public boolean isCredentials(String login, String password) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("checkUser"))) {
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean isUserHaveMusicType(User user, MusicType musicType) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectMusicByUser"))) {
            pst.setInt(1, user.getId());
            pst.setInt(2, musicType.getMusic_id());
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }


        return result;
    }

}

