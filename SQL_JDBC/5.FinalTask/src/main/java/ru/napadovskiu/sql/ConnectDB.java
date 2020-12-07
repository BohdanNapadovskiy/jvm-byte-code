package ru.napadovskiu.sql;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import ru.napadovskiu.vacancy.Vacancy;
import ru.napadovskiu.settings.Settings;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;


/**
 * Package of final task SQL_JDBC.
 * Class for connection to sql db .
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.02.2018
 */
public class ConnectDB {

    /**
     * URL to DB.
     */
    private String url;

    /**
     * DB user.
     */
    private String user;

    /**
     * DB user pass.
     */
    private String password;


    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConnectDB.class);

    /**
     * settings.
     */
    private final Settings settings = new Settings();

    /**
     * Constructor for class.
     */
    public ConnectDB() {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
        this.url = this.settings.getValue("url");
        this.user = this.settings.getValue("userName");
        this.password = this.settings.getValue("password");
    }

    /**
     *Method check first launch.
     * @return
     */
    public boolean itIsFirstLaunch() {
        boolean result = true;

        try (Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM table_vacancy");) {
            ResultSet resultQuery =  pst.executeQuery();
            if (resultQuery.next()) {
                result = false;
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Method add vacancy in db.
     * @param vacancy
     */
    public void addVacancy(Vacancy vacancy) {
        try (Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
             PreparedStatement pst = connection.prepareStatement(this.settings.getValue("insertValue"));)  {
            pst.setTimestamp(1, vacancy.getVac_Date());
            pst.setString(2, vacancy.getVac_Link());
            pst.setString(3, vacancy.getVac_author());
            pst.setString(4, vacancy.getVac_Description());
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Method check exist vacancy in db.
     * @param vacancy vacancy for check
     * @return result.
     */
    public boolean vacancyExist(Vacancy vacancy) {
        boolean result = false;
        try (Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
            PreparedStatement pst = connection.prepareStatement(this.settings.getValue("selectVacancy"))) {
            pst.setString(1, vacancy.getVac_Description());
            pst.setString(2, vacancy.getVac_author());
            ResultSet resultQuery =  pst.executeQuery();
            result = resultQuery.next();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }




    /**
     * Method return last date vacancy.
     * @return date.
     */
    public Timestamp getDateLastVacancy() {
        Timestamp date = null;
        try (Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
             PreparedStatement pst = connection.prepareStatement("SELECT vacancy_date FROM table_vacancy ORDER BY vacancy_date DESC LIMIT 1")) {
            ResultSet resultQuery =  pst.executeQuery();
            if (resultQuery.next()) {
                date = resultQuery.getTimestamp("vacancy_date");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * Method create data base.
     */
    public void createDateBase() {

        try (Connection connection = DriverManager.getConnection(this.url,this.user,this.password);
             Statement statement = connection.createStatement();)  {
            statement.addBatch(this.settings.getValue("createTable"));
            statement.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
