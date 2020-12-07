package ru.napadovskiu.filemanager.menufilemanager;

import ru.napadovskiu.filemanager.client.Client;
import ru.napadovskiu.filemanager.server.Server;

import java.io.IOException;

/**
 * Created by program on 30.01.2017.
 */
public class StartFileManager {
    private Server server;
    private Input input;
    private Client client;


    public StartFileManager(Input input, Client client, Server server) {
        this.input = input;
        this.server = server;
        this.client = client;
    }


    public static void main(String[] args) throws IOException {
        Client client = new Client();
        Server server = new Server();
        Input input = new ConsoleInput();
        StartFileManager startFileManager = new StartFileManager(input, client, server);


    }

}
