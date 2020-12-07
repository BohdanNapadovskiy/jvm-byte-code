package ru.napadovskiu.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.entities.Address;
import ru.napadovskiu.entities.Role;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.settings.Settings;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoleStore implements AbstractStore<Role> {

    /**
     * settings for load.
     */
    private final Settings createQuery = new Settings();

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(RoleStore.class);



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

    /**
     *
     */
    private final Settings updateQuery = new Settings();

    public static final RoleStore INSTANCE = new RoleStore();

    public RoleStore() {
        ClassLoader loader = Settings.class.getClassLoader();
        this.selectQuery.load(loader.getResourceAsStream("select.properties"));
        this.insertQuery.load(loader.getResourceAsStream("insert.properties"));
        this.deleteQuery.load(loader.getResourceAsStream("delete.properties"));
        this.updateQuery.load(loader.getResourceAsStream("update.properties"));
    }


    @Override
    public Role getById(int id) {
        Role role = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectRole")))  {
            pst.setInt(1, id);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                role = new Role(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return role;

    }

    @Override
    public Role getByName(String name) {
        Role role = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectRoleByName")))  {
            pst.setString(1, name);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                role = new Role(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return role;

    }


    @Override
    public List<Role> getAll() {
        List<Role> result = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAllRoles"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(new Role(resultQuery));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean create(Role role) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.insertQuery.getValue("insertRole"))) {
            pst.setString(1, role.getUser_role());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean update(Role role) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateRole"))) {
            pst.setString(1, role.getUser_role());
            pst.setInt(2, role.getRole_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean delete(Role role) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.deleteQuery.getValue("deleteRole"));)  {
            pst.setInt(1, role.getRole_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

}
