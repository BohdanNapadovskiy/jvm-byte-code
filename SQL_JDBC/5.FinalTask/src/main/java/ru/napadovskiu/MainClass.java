package ru.napadovskiu;

import ru.napadovskiu.parsingHTML.ParsingHTML;
import ru.napadovskiu.settings.Settings;
import java.io.InputStream;
import java.util.Timer;

/**
 * Package of final task SQL_JDBC.
 * Main class for starting parsing.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.02.2018
 */
public class MainClass {


    /**
     * Main method for starting programm.
     * @param args
     */
    public static void main (String[] args) {
        ClassLoader loader = Settings.class.getClassLoader();
        InputStream io = loader.getResourceAsStream("app.properties");
        Settings settings = new Settings();
        settings.load(io);
        int count = Integer.parseInt(settings.getValue("countLaunch"));

        Timer timer = new Timer();
        ParsingHTML parsingHTML = new ParsingHTML();

        timer.scheduleAtFixedRate(parsingHTML,0, 60 * 60 * 24 * 1000 / count);

    }

}
