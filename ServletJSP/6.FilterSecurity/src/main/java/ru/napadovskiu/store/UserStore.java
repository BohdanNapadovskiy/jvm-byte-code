package ru.napadovskiu.store;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.settings.Settings;
import ru.napadovskiu.users.User;
import ru.napadovskiu.users.UserRole;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


public class UserStore {

    /**
     * instance of class.
     */
    private static final UserStore INSTANCE = new UserStore();

    /**
     * password for connect to db.
     */
    private final String password;

    /**
     * url for connect to db.
     */
    private final String url;

    /**
     * user for connect to db.
     */
    private final String user;

    /**
     * settings for load.
     */
    private final Settings settings = new Settings();

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    /**
     *
     */
    private BasicDataSource ds;

    /**
     * Constructor for class.
     */
    private UserStore()  {
        this.ds = new BasicDataSource();
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        this.url = this.settings.getValue("url");
        this.user = this.settings.getValue("userName");
        this.password = this.settings.getValue("password");

        this.ds.setDriverClassName("org.postgresql.Driver");
        this.ds.setUrl(this.url);
        this.ds.setUsername(this.user);
        this.ds.setPassword(this.password);
        this.ds.setMaxIdle(1000);
        this.ds.setMinIdle(100);

    }

    /**
     * Method return instance class
     * @return instance
     */
    public static UserStore getInstance() {

        return INSTANCE;

    }

    /**
     * Method init connection to db.
     */
    public void initDB() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.settings.getValue("createTable"));
            statement.executeBatch();
            statement.addBatch(this.settings.getValue("createTableRole"));
            statement.executeBatch();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     *
     * @param resultQuery
     * @return
     * @throws SQLException
     */
    private User createUserFromQuery(ResultSet resultQuery) throws SQLException {
        int userId = resultQuery.getInt("user_id");
        String userName = resultQuery.getString("user_name");
        String userLogin = resultQuery.getString("user_login");
        String userEmail = resultQuery.getString("user_email");
        String userPassword = resultQuery.getString("user_password");
        String user_country = resultQuery.getString("user_country");
        String user_city = resultQuery.getString("user_city");
        Timestamp userCreateDate = resultQuery.getTimestamp("user_createDate");

        User newUser = new User(userId, userName, userLogin, userEmail, userCreateDate, userPassword, user_country, user_city);

        UserRole role = selectUserRole(newUser);

        newUser.setRole(role);

        return newUser;
    }

    /**
     * Method add user to db.
     * @param user for add in to db.
     * @return result true or false.
     */
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("insertUser")))  {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setTimestamp(4, user.getCreateDate());
            pst.setString(5, user.getPassword());
            pst.setString(6, user.getCountry());
            pst.setString(7, user.getCity());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean deleteUser(User user) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("deleteUser"));)  {
            pst.setInt(1, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * Method update user in db.
     * @param name new name of users.
     * @param login new login for users.
     * @param email for find users in db.
     * @return result true or false.
     */
    public boolean updateUser(String oldName, String oldLogin, String oldEmail,  String name, String login, String email, String password, String country, String city) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("updateUser"));)  {
            pst.setString(1, login);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, password);
            pst.setString(5, country);
            pst.setString(6, city);


            pst.setString(7, oldLogin);
            pst.setString(8, oldName);
            pst.setString(9, oldEmail);


            result = pst.executeUpdate() != 0;

        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Method select all users from db.
     * @return array with users.
     */
    public CopyOnWriteArrayList<User> selectAllUser() {
        CopyOnWriteArrayList<User> result = new CopyOnWriteArrayList<>();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectAllUser"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                User newUser = createUserFromQuery(resultQuery);
                result.add(newUser);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     *
     * @param userId
     * @return
     */
    public User selectUser(int userId) {
        User user = null;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectUser")))  {
            pst.setInt(1, userId);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = createUserFromQuery(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     *
     * @param email
     * @return
     */
    public User selectUser(String email) {
        User user = null;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectUserByEmail")))  {
             pst.setString(1, email);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = createUserFromQuery(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public User selectUser(String login, String password) {
        User user = null;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("checkUser")))  {
            pst.setString(1, login);
            pst.setString(2, password);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                user = createUserFromQuery(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    public boolean isCredentials(String login, String password) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("checkUser")))  {
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

    /**
     *
     * @param userEmail
     * @param roleName
     * @return
     */
    public boolean addRole(String userEmail, String roleName) {
        boolean result = false;
        User searchUser = selectUser(userEmail);

        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("addUserRole")))  {
            pst.setInt(1, searchUser.getId());
            pst.setString(2, roleName);
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    /**
     *
     * @param userId
     * @param roleName
     * @return
     */
    public boolean updateRole(int userId, String roleName) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("updateUserRole")))  {
            pst.setString(1, roleName);
            pst.setInt(2, userId);
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * @return
     */
    public CopyOnWriteArraySet selectCities() {
        CopyOnWriteArraySet<String> result = new CopyOnWriteArraySet<>();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectAllUser")))  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(resultQuery.getString("user_city"));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * @return
     */
    public CopyOnWriteArraySet selectCountries() {
        CopyOnWriteArraySet<String> result = new CopyOnWriteArraySet<>();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectAllUser")))  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(resultQuery.getString("user_country"));
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     * @param user
     * @return
     */
    public UserRole selectUserRole(User user) {
        UserRole userRole = null;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectUserRole")))  {
            pst.setInt(1, user.getId());
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                String roleName = resultQuery.getString("user_role");
                userRole = new UserRole(roleName);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return userRole;

    }



    /**
     *
     * @param user
     * @return
     */
    public boolean deleteUserRole(User user) {
        boolean result;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("deleteUserRole")))  {
            pst.setInt(1, user.getId());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     *
     */
    public void closeConnection() {
        try {
            this.ds.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}

