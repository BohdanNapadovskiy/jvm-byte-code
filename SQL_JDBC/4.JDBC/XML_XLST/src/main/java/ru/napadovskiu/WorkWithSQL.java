package ru.napadovskiu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.setings.Settings;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


/**
 * Class for work with SQL
 */
public class WorkWithSQL {

    /**
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkWithSQL.class);

    /**
     * settings for connection to db.
     */
    private final Settings settings = new Settings();

    /**
     * Connection for DB.
     */
    private Connection connection = null;

    /**
     * count of values.
     */
    private int countOfValues;

    /**
     *
     */
    private final InputStream io;

    /**
     * Constructor for class.
     */
    public WorkWithSQL(int count) {
        ClassLoader loader = Settings.class.getClassLoader();
        this.io = loader.getResourceAsStream("app.properties");
        this.countOfValues = count;
        this.settings.load(io);

    }

    /**
     * Method connect to db.
     */
    private void connectToDB() {
        try {
           this.connection = DriverManager.getConnection(this.settings.getValue("url"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method create table in db.
     */
    public void createTable() {
        connectToDB();
        try (Statement st = this.connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS field (id INTEGER PRIMARY KEY, value INTEGER NOT NULL )");
            st.executeBatch();

            deleteValuesFromTable();

            insertValueInTable(this.countOfValues);

            createFirstXmlFile();

            closeConnection();
        } catch (SQLException   e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method delete values from table.
     */
    private void deleteValuesFromTable() {
        try (PreparedStatement st = this.connection.prepareStatement("DELETE FROM field;")) {
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method insert values to table.
     * @param count cont of values.
     */
    private void insertValueInTable(int count) {

        int i = 0;

        try (PreparedStatement st = this.connection.prepareStatement("INSERT INTO field (id,value) VALUES(?,?);")) {
            this.connection.setAutoCommit(false);
            while (i <= count) {
                st.setInt(1, i);
                st.setInt(2, i);
                st.addBatch();
                i++;
            }
            st.executeBatch();
            this.connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Method create xml file.
     */
    private void createFirstXmlFile() {
        WorkWithXMLFile workWithXMLFile = new WorkWithXMLFile();
        File firstXmlFile = workWithXMLFile.createXmlFile(this.settings.getValue("firstXML"));

        XMLOutputFactory output = XMLOutputFactory.newInstance();

        try  (PreparedStatement pst = this.connection.prepareStatement("SELECT value FROM field;");) {
            XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(firstXmlFile));
            ResultSet resultOfQuery =  pst.executeQuery();
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeStartElement("entries");

            while (resultOfQuery.next()) {
                writer.writeStartElement("entry");
                writer.writeStartElement("field");
                writer.writeCharacters(String.valueOf(resultOfQuery.getInt("value")));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            // Закрываем XML-документ
            writer.flush();
        } catch (SQLException | XMLStreamException | IOException  e) {
            LOG.error(e.getMessage(), e);
        }
    }


    /**
     * Method close connection with db.
     */
    private void closeConnection() {
        try {
            this.connection.close();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }




}
