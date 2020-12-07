package ru.napadovskiu.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by program on 31.01.2017.
 */
public class Settings {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Settings.class);

    /**
     * properties.
     */
    private final Properties prs = new Properties();

    /**
     * Method to load properties.
     * @param io input stream.
     */
    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method return value for key from properties.
     * @param key key for search
     * @return result value.
     */
    public String getValue(String key) {
        return this.prs.getProperty(key);
    }

}
