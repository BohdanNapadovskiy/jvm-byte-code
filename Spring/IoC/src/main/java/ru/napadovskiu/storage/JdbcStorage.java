package ru.napadovskiu.storage;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.napadovskiu.models.User;
import org.hibernate.Session;

import java.util.function.Function;

public class JdbcStorage implements Storage<User> {

    private final SessionFactory factory;

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(UserStorage.class);


    public JdbcStorage(String resource) {
        this.factory = new Configuration().configure(resource).buildSessionFactory();
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





    @Override
    public Integer add(User user) {
        return this.tx(session -> {
            session.save(user);
            return user.getUserId();
        });


    }

    @Override
    public User get(Integer id) {
        return this.tx(session -> (User) session.get(User.class, id));
    }
}
