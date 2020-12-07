package ru.napadovskiu.storage;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.napadovskiu.entities.Transmission;
import ru.napadovskiu.entities.User;

import java.util.List;
import java.util.function.Function;

public class UserStorage implements Storage<User> {


    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(UserStorage.class);

    /**
     *
     */
    private static final UserStorage INSTANCE = new UserStorage();


    /**
     *
     */
    private UserStorage() {
    }

    /**
     *
     * @return
     */
    public static UserStorage getInstance() {
        return INSTANCE;
    }



    private <T> T tx(final Function<Session, T> command) {
        final Session session = HibernateFactory.getInstance().openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) {
        return this.tx(session -> {
            session.save(user);
            return true;
        });
    }


    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean delete(User user) {
        return this.tx(session -> {
            session.delete(user);
            return true;
        });
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        return this.tx(session -> {
            session.save(user);
            return user.getUserId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public User get(int id) {
        return this.tx(session -> (User) session.get(User.class, id));

    }

    public User getUserByNameAndLogin(String login, String password) {
        return this.tx(session -> {
            User user = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.User WHERE user_name =:name AND user_password =:password");
            query.setParameter("name", login);
            query.setParameter("password", password);
            List<User> userList = query.getResultList();
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            return user;
        });

    }


    @Override
    public User getByName(String name) {
        return this.tx(session -> {
            User user = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.User WHERE user_name =:name");
            query.setParameter("name", name);
            List<User> userList = query.getResultList();
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
            return user;
        });

    }


    /**
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.entities.User").list());
    }

    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }


    public Boolean isCredentials(String name, String password) {

        return this.tx(session -> {
            Boolean result = false;
            
            Query query  = session.createQuery("from ru.napadovskiu.entities.User WHERE userName=:name and userPassword =:password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            if (query.getResultList().size() != 0 ) {
                result = true;
            }
            return result;
        });
    }
}
