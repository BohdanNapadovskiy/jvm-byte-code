package ru.napadovskiu.filemanager.client;

import ru.napadovskiu.filemanager.menufilemanager.Settings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by program on 04.01.2017.
 */
public class Client  {

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void connect() {
        Settings clientSettings = new Settings();

        try {
            InetAddress inetAddress = InetAddress.getByName(clientSettings.getInterAdress());
            System.out.println("Подключаемся к серверу");
            Socket socket = new Socket(inetAddress, clientSettings.getServerPort());

            InputStream socketIn  = socket.getInputStream();
            OutputStream socketOut = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(socketIn);
            DataOutputStream dataOutputStream = new DataOutputStream(socketOut);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (socket.isConnected()) {
                String string = reader.readLine();
                dataOutputStream.writeUTF(string);
                dataOutputStream.flush();
                string = dataInputStream.readUTF();
                System.out.println(string);
             }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        Client startFileManager = new Client();
        startFileManager.connect();
   }

}
