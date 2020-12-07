package ru.napadovskiu.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {

    private  int role_id;


    private String user_role;


    public Role(String user_role) {
        this.user_role = user_role;
    }

    public Role(int role_id, String user_role) {
        this.role_id = role_id;
        this.user_role = user_role;
    }

    public Role (ResultSet resultQuery) throws SQLException {
        int role_id = resultQuery.getInt("role_id");
        String user_role = resultQuery.getString("user_role");
        this.role_id = role_id;
        this.user_role = user_role;

    }

    public int getRole_id() {
        return this.role_id;
    }

    public String getUser_role() {
        return this.user_role;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
