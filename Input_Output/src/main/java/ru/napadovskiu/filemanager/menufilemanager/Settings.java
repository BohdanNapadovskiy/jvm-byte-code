package ru.napadovskiu.filemanager.menufilemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by program on 31.01.2017.
 */
public class Settings {

    private String returnPathOfProperty() {
        String result = System.getProperty("user.dir");

        String checkString = "Input_Output";
        if (!result.contains(checkString)) {
            result = result + "\\" + checkString;
        }

        return result;
    }

    public String getInterAdress()  throws IOException {
        String result = null;

        Properties props = new Properties();

        props.load(new FileInputStream(new File(returnPathOfProperty() + "\\app.properties")));

        result     = props.getProperty("serverAddress");

        return result;

    }

    public String getParentDir()  throws IOException {
        String result = null;

        Properties props = new Properties();

        props.load(new FileInputStream(new File(returnPathOfProperty() + "\\app.properties")));

        result  = props.getProperty("serverHomePath");

        return result;

    }

    public String getClientHomePath()  throws IOException {
        String result = null;

        Properties props = new Properties();

        props.load(new FileInputStream(new File(returnPathOfProperty() + "\\app.properties")));

        result  = props.getProperty("clientHomePath");

        return result;

    }



    public int getServerPort() throws IOException {

        int result = 0;

        Properties props = new Properties();

        props.load(new FileInputStream(new File(returnPathOfProperty() + "\\app.properties")));

        String strServerPort     = props.getProperty("port");

        result = Integer.valueOf(strServerPort);

        return result;

    }
}
