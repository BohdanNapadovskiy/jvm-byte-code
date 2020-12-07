package ru.napadovskiu.filemanager.menufilemanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * Created by program on 04.01.2017.
 */
public class Menu {

    private UserAction[] actions = new UserAction[6];

    private Input input;

    private DataInputStream inStr;

    private DataOutputStream outStr;

    private String serverCurrentDirectory;

    private String clientCurrentDirectory;

    public Menu(Input input, DataInputStream inStr, DataOutputStream outStr, String currentDirectory) {
        this.input = input;
        this.inStr = inStr;
        this.outStr = outStr;
        this.serverCurrentDirectory = currentDirectory;
        setClientCurrentDirectory();

    }

    private  void setClientCurrentDirectory() {
        Settings settings = new Settings();

        try {
            this.clientCurrentDirectory = settings.getClientHomePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void fillAction() {
        this.actions[0] = new ShowMenu();
        this.actions[1] = new ShowAllFiles();
        this.actions[2] = new GoToParentDirectory();
        this.actions[3] = new GoToSubDirectory();
        this.actions[4] = new UploadFile();
        this.actions[5] = new DownLoadFile();
    }

    public void doAction(String strings) throws IOException {
        String[] message = strings.split(" ");
        String action = message[0];

        if (action.equals("help")) {
            this.actions[0].execute(action);
        } else if (action.equals("dir")) {
            this.actions[1].execute(action);
        } else if (action.equals("cd")) {
            this.actions[2].execute(action);
        } else if (action.equals("download")) {
            String fileName = message[1];
            this.actions[5].execute(fileName);
        } else if (action.equals("upload")) {
            String fileName = message[1];
            this.actions[4].execute(fileName);
        } else if (action.equals("cd..")) {
            String newDir = message[1];
            this.actions[3].execute(newDir);
        }
    }

     /**
     *The method show users menu
     */
    public void showMenu() {
        System.out.println("help" + " Show menu file manager");
        System.out.println("dir" + " Show all files current directory");
        System.out.println("cd"  + " Go to parent directory ");
        System.out.println("cd.. <name sub dir>" + " Go to sub directory ");
        System.out.println("download <file name>" + " Download file ");
        System.out.println("upload <file name>" + " Upload file  ");
        System.out.println("exit" + " Exit");
    }

    private class ShowMenu  implements  UserAction {

        public String key() {
            return "help";
        }

        public void execute(String string) throws IOException {
            showMenu();
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all files current directory");
        }

    }

    private class ShowAllFiles  implements  UserAction {

        public String key() {
            return "dir";
        }

        public void execute(String string) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            String separator = " ";
            File dir = new File(serverCurrentDirectory);

            for (File file : dir.listFiles()) {
                stringBuilder.append(file.getName());
                stringBuilder.append(separator);
            }
            outStr.writeUTF(stringBuilder.toString());

        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all files current directory");
        }

    }

    private class GoToSubDirectory  implements  UserAction {

        public String key() {
            return "cd..";
        }

        public void execute(String string) throws IOException {
            String newDir = "\\" + string + "\\";
            File currentPath = new File(serverCurrentDirectory + newDir);
            if (currentPath.isDirectory()) {
                outStr.writeUTF("Пермещение в каталог " + currentPath.getPath());
                serverCurrentDirectory = currentPath.toString();
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all menu");
        }

    }

    private class GoToParentDirectory implements  UserAction {

        public String key() {
            return "cd";
        }

        public void execute(String string) throws IOException {
            File currentPath = new File(serverCurrentDirectory);
            if (currentPath.getPath().length() <= 3) {
                outStr.writeUTF("Нельзя переместиться");
            } else {
                currentPath = new File(currentPath.getParent());
                outStr.writeUTF("Пермещение в каталог " + currentPath.getPath());
                serverCurrentDirectory = currentPath.toString();
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all menu");
        }

    }

    private class UploadFile implements  UserAction {

        public String key() {
            return "upload";
        }

        public void execute(String string) throws IOException {
            InputStream in = null;
            OutputStream out = null;
            try {
                File fileSource = new File(serverCurrentDirectory + "\\" + string);
                File fileDest   = new File(clientCurrentDirectory + "\\" + string);
                in = new FileInputStream(fileSource);
                out = new FileOutputStream(fileDest);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = in.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                }
            } finally {
                out.flush();
                out.close();
            }
            outStr.writeUTF("Загрузка файла с сервера");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all menu");
        }

    }

    private class DownLoadFile implements  UserAction {

        public String key() {
            return "download";
        }

        public void execute(String string) throws IOException {
            InputStream in = null;
            OutputStream out = null;
            try {
                File fileSource = new File(clientCurrentDirectory + "\\" + string);
                File fileDest   = new File(serverCurrentDirectory + "\\" + string);
                in = new FileInputStream(fileSource);
                out = new FileOutputStream(fileDest);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = in.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                }
            } finally {
                out.flush();
                out.close();
            }
            outStr.writeUTF("Загрузка файла с сервера");


        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all menu");
        }

    }

}
