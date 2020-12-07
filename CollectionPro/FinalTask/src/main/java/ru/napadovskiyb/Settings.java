package ru.napadovskiyb;

import java.io.InputStream;
import java.util.Properties;

/**
 * Package of CollectionPro finalTask.
 * Class for take settngs for load file.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 09.08.2017
 */
public class Settings {

    /**
     * Properties.
     */
    private final Properties properties = new Properties();

    /**
     *Method load properties.
     * @param is input stream.
     */
    public void load(InputStream is) {
        try {
            this.properties.load(is);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }


    /**
     * Method return home path.
     * @param key in properties.
     * @return name of file.
     */
    public String getValue(String key) {
        return this.properties.getProperty(key);

    }
}
