package ru.napadovskiu.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.entities.Address;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.settings.Settings;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AddressStore implements AbstractStore<Address> {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AddressStore.class);



    /**
     * settings for load.
     */
    private final Settings createQuery = new Settings();


    /**
     *
     */
    private final Settings selectQuery = new Settings();


    /**
     *
     */
    private final Settings insertQuery = new Settings();

    /**
     *
     */
    private final Settings deleteQuery = new Settings();


    private final Settings updateQuery = new Settings();

    public static final AddressStore INSTANCE = new AddressStore();

    /**
     *
     */


    public AddressStore() {

        ClassLoader loader = Settings.class.getClassLoader();
        this.selectQuery.load(loader.getResourceAsStream("select.properties"));
        this.insertQuery.load(loader.getResourceAsStream("insert.properties"));
        this.deleteQuery.load(loader.getResourceAsStream("delete.properties"));
        this.updateQuery.load(loader.getResourceAsStream("update.properties"));

    }

    @Override
    public Address getById(int id) {
        Address address = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAddress")))  {
            pst.setInt(1, id);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                address = new Address(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return address;

    }

    @Override
    public Address getByName(String strAddress) {
        Address address = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAddressByName")))  {
            pst.setString(1, strAddress);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                address = new Address(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return address;

    }


    @Override
    public List<Address> getAll() {
        List<Address> result = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAllAddress"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                Address address = new Address(resultQuery);
                result.add(address);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean create(Address address) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.insertQuery.getValue("insertAddress"))) {
            pst.setString(1, address.getAddress_name());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean update(Address address) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateAddress"))) {
            pst.setString(1, address.getAddress_name());
            pst.setInt(2, address.getAddress_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public boolean delete(Address address) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.deleteQuery.getValue("deleteAddress"));)  {
            pst.setInt(1, address.getAddress_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

}

