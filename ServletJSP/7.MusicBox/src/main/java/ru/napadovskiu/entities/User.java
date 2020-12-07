package ru.napadovskiu.entities;

import ru.napadovskiu.store.AddressStore;
import ru.napadovskiu.store.MusicStore;
import ru.napadovskiu.store.RoleStore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class User {

    private int id;

    private String name;

    private String login;

    private String password;

    private Address address;

    private Role role;

    private List<MusicType> musicType;

    public User(String user_name, String user_login) {
        this.name = user_name;
        this.login = user_login;
    }

    public User (ResultSet resultQuery) throws SQLException {
        RoleStore roleStore = new RoleStore();
        AddressStore addressStore = new AddressStore();
        MusicStore musicStore = new MusicStore();
        int userId = resultQuery.getInt("user_id");

        String userName = resultQuery.getString("user_name");
        String userLogin = resultQuery.getString("user_login");

        this.id = userId;
        this.name = userName;
        this.login = userLogin;

        String userPassword = resultQuery.getString("user_password");

        this.setPassword(userPassword);

        int address_id = resultQuery.getInt("address_id");
        int role_id = resultQuery.getInt("role_id");

        Address address = addressStore.getById(address_id);
        Role role = roleStore.getById(role_id);

        this.setRole(role);
        this.setAddress(address);

        this.setMusicType(musicStore.getAllMusicTypeByUser(userId));
    }

    public User(int user_id, String user_name, String user_login) {
        this.id = user_id;
        this.name = user_name;
        this.login = user_login;
    }

    public void setPassword(String user_password) {
        this.password = user_password;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }

    public void setLogin(String user_login) {
        this.login = user_login;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMusicType(List<MusicType> musicType) {

        this.musicType = musicType;
    }

    public Role getRole() {
        return this.role;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getPassword() {
        return this.password;
    }

    public List<MusicType > getMusicType() {
        return this.musicType;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }



}