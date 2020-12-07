package ru.napadovskiu.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.napadovskiu.entities.Address;
import ru.napadovskiu.entities.MusicType;
import ru.napadovskiu.entities.Role;
import ru.napadovskiu.entities.User;
import ru.napadovskiu.settings.Settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MusicStore implements AbstractStore<MusicType> {

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

    /**
     *
     */
    private final Settings updateQuery = new Settings();


    public static final MusicStore INSTANCE = new MusicStore();

    public MusicStore() {

        ClassLoader loader = Settings.class.getClassLoader();
        this.createQuery.load(loader.getResourceAsStream("create.properties"));
        this.selectQuery.load(loader.getResourceAsStream("select.properties"));
        this.insertQuery.load(loader.getResourceAsStream("insert.properties"));
        this.deleteQuery.load(loader.getResourceAsStream("delete.properties"));
        this.updateQuery.load(loader.getResourceAsStream("update.properties"));
    }



    @Override
    public MusicType getById(int id) {
        MusicType musicType = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectMusic")))  {
            pst.setInt(1, id);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                musicType = new MusicType(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return musicType;


    }

    @Override
    public List<MusicType> getAll() {
        List<MusicType> result = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAllMusic"));)  {
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(new MusicType(resultQuery));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public MusicType getByName(String name) {
        MusicType musicType = null;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectMusicByName")))  {
            pst.setString(1, name);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                musicType = new MusicType(resultQuery);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return musicType;
    }

    @Override
    public boolean create(MusicType music) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.insertQuery.getValue("insertMusic"))) {
            pst.setString(1, music.getMusic_name());
            result =pst.executeUpdate() != 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean update(MusicType music) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.updateQuery.getValue("updateMusic"))) {
            pst.setString(1, music.getMusic_name());
            pst.setInt(2, music.getMusic_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    @Override
    public boolean delete(MusicType music) {
        boolean result = false;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.deleteQuery.getValue("deleteMusic"));)  {
            pst.setInt(1, music.getMusic_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public boolean addMusicToUser(User user, MusicType musicType) {
        boolean result;
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.insertQuery.getValue("insertUserToMusic"))) {
            pst.setInt(1, user.getId());
            pst.setInt(2, musicType.getMusic_id());
            result = pst.executeUpdate() != 0;
        } catch (SQLException e) {
            result = false;
            LOG.error(e.getMessage(), e);
        }
        return result;

    }

    public List<MusicType> getAllMusicTypeByUser(int user_id) {
        List<MusicType> result = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionDB.INSTANCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(this.selectQuery.getValue("selectAllMusicByUser"));)  {
            pst.setInt(1, user_id);
            ResultSet resultQuery =  pst.executeQuery();
            while (resultQuery.next()) {
                result.add(getById(resultQuery.getInt("music_id")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
