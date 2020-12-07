package ru.napadovskiu.store;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.settings.Settings;
import ru.napadovskiu.users.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.concurrent.CopyOnWriteArrayList;


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
        this.ds.setUsername(this.user );
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
        try (Connection connection = this.ds.getConnection();//DriverManager.getConnection(this.url, this.user, this.password);
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.settings.getValue("createTable"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Method add user to db.
     * @param user for add in to db.
     * @return result true or false.
     */
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("insertUser"));)  {
            pst.setTimestamp(1, user.getCreateDate());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getLogin());
            pst.setString(4, user.getName());
            result = pst.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Method delete users from db.
     * @param email for check users in db.
     * @return result true or false.
     */
    public boolean deleteUser(String email) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("deleteUser"));)  {
            pst.setString(1, email);
            result = pst.executeUpdate() != 0 ? true : false;
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
    public boolean updateUser(String name, String login, String email) {
        boolean result = false;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("updateUser"));)  {
            pst.setString(1, login);
            pst.setString(2, name);
            pst.setString(3, email);
            result = pst.executeUpdate() != 0 ? true : false;

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
        CopyOnWriteArrayList<User> result = new CopyOnWriteArrayList<User>();
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectAllUser"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                String userName = resultQuery.getString("user_name");
                String userLogin = resultQuery.getString("user_login");
                String userEmail = resultQuery.getString("user_email");
                Timestamp userCreateDate = resultQuery.getTimestamp("user_createDate");

                User newUser = new User(userName, userLogin, userEmail, userCreateDate);
                result.add(newUser);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Method search user in db
     * @param email for search
     */
    public User selectUser(String email) {
        User user = null;
        try (Connection connection = this.ds.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectUser"));)  {
            pst.setString(1, email);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                String userName = resultQuery.getString("user_name");
                String userLogin = resultQuery.getString("user_login");
                String userEmail = resultQuery.getString("user_email");
                Timestamp userCreateDate = resultQuery.getTimestamp("user_createDate");

                user = new User(userName, userLogin, userEmail, userCreateDate);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public void closeConnection() {
        try {
            this.ds.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}

