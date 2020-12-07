package ru.napadovskiu.workwithsql;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class WorkWithSQL {

    /**
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkWithSQL.class);

    /**
     *
     */
    private final Settings settings = new Settings();

    /**
     *
     */
    private final ClassLoader loader;


    /**
     *
     */
    public WorkWithSQL() {
        this.loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        this.settings.load(io);
    }


    /**
     *
     */
    public void createDateBase() {
        File fileQuery1 = new File(this.loader.getResource("app.properties").getFile());

        File fileQuery =  new File(fileQuery1.getParent() + (settings.getValue("createDataBaseQuery")));

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileQuery.getAbsoluteFile(), "r");
            Connection connection = DriverManager.getConnection(this.settings.getValue("url"), this.settings.getValue("userName"), this.settings.getValue("password"));) {
            Statement st = connection.createStatement();

            StringBuffer sb  = new StringBuffer();
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
            sb.append(line);
                if (line.endsWith(";")) {
                    st.addBatch(sb.toString());
                    sb = new StringBuffer();
                }
            }
            st.executeBatch();
            connection.close();
            randomAccessFile.close();
        } catch (IOException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
