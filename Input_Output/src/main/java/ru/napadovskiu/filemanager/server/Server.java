package ru.napadovskiu.filemanager.server;

import ru.napadovskiu.filemanager.menufilemanager.ConsoleInput;
import ru.napadovskiu.filemanager.menufilemanager.Input;
import ru.napadovskiu.filemanager.menufilemanager.Menu;
import ru.napadovskiu.filemanager.menufilemanager.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by program on 05.01.2017.
 */
public class Server {

//    DataInputStream dataInputStream;
//    DataOutputStream dataOutputStream;
//    Input input;
    private String currentDirectory;


    public String listDirectory() {
        String result = null;

        return result;
    }

    /**
     * @return
     */
    private String getParrentDirrectory() throws IOException {
        String result = null;

        Settings settings = new Settings();

        result = settings.getParentDir();

        return result;
    }


    /**
     * @return
     */
    public String goToChildDirrectory() {
        String result = null;

        return result;

    };

    public void downloadFile(Socket socket, String pathFile, int fileSize) {

    }

    public void upLoadFile(Socket socket, File file) {

    }

    public void connect() throws  IOException {
       boolean exit = false;

       Properties props = new Properties();

        Settings settings = new Settings();

        int serverPort = Integer.valueOf(settings.getServerPort());

        this.currentDirectory = settings.getParentDir();

        try {

            ServerSocket serverSocket = new ServerSocket(serverPort);

            Socket socket = serverSocket.accept();

            InputStream socketIn  = socket.getInputStream();
            OutputStream socketOut = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(socketIn);
            DataOutputStream dataOutputStream = new DataOutputStream(socketOut);

            String string = null;
            Input input = new ConsoleInput();

            Menu menu = new Menu(input, dataInputStream, dataOutputStream, this.currentDirectory);
            menu.fillAction();
            do {
                string = dataInputStream.readUTF();
                String[] message = string.split(" ");
                String action = message[0];

                System.out.println("Сервер получил" + string);
                if (string.equals("exit")) {
                    exit = true;
                } else {
                    menu.doAction(string);
                }
            } while (!exit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.connect();
    }

}
