package ru.napadovskiu.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
    private int address_id;

    private String address_name;

    public Address(int address_id, String address_name) {
        this.address_id = address_id;
        this.address_name = address_name;
    }


    public Address (ResultSet resultQuery) throws SQLException {
        int addressId = resultQuery.getInt("address_id");
        String addressName = resultQuery.getString("address_name");
        this.address_id = addressId;
        this.address_name = addressName;
    }


    public Address(String address_name) {
        this.address_name = address_name;
    }

    public int getAddress_id() {
        return this.address_id;
    }

    public String getAddress_name() {
        return this.address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }


}
