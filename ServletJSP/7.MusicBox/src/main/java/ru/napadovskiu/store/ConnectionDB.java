package ru.napadovskiu.store;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.settings.Settings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    public static final ConnectionDB INSTANCE = new ConnectionDB();


    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private BasicDataSource ds;

    private final Settings connectToDB = new Settings();

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
    private final Settings createQuery = new Settings();



    private ConnectionDB() {
        this.ds = new BasicDataSource();
        ClassLoader loader = Settings.class.getClassLoader();
        this.connectToDB.load(loader.getResourceAsStream("connectTodb.properties"));
        this.createQuery.load(loader.getResourceAsStream("create.properties"));


        this.url = this.connectToDB.getValue("url");
        this.user = this.connectToDB.getValue("userName");
        this.password = this.connectToDB.getValue("password");

        this.ds.setDriverClassName("org.postgresql.Driver");
        this.ds.setUrl(this.url);
        this.ds.setUsername(this.user);
        this.ds.setPassword(this.password);
        this.ds.setMaxIdle(1000);
        this.ds.setMinIdle(100);

    }

    private void createTableUser() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.createQuery.getValue("createTableUser"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableMusicType() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.createQuery.getValue("createTableMusic"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableUserToMusic() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.createQuery.getValue("createTableUserToMusic"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableRole() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.createQuery.getValue("createTableRole"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableAddress() {
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.createQuery.getValue("createTableAddress"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        createTableRole();
        createTableAddress();
        createTableMusicType();
        createTableUser();
        createTableUserToMusic();

        return this.ds.getConnection();
    }

    public void closeConnection() throws SQLException {
        this.ds.close();
    }



}
