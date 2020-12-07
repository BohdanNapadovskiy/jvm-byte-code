package ru.napadovskiu.storage;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.napadovskiu.entities.Transmission;

import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class TransStorage implements Storage<Transmission> {


    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(TransStorage.class);


    /**
     *
     */
    private static final TransStorage INSTANCE = new TransStorage();


    /**
     *
     */
    private TransStorage() {
    }

    /**
     *
     * @return
     */
    public static TransStorage getInstance() {
        return INSTANCE;
    }

    /**
     *
     * @param command
     * @param <T>
     * @return
     */
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
     * @param transmission
     * @return
     */
    @Override
    public boolean update(Transmission transmission) {
        return this.tx(session -> {
            session.save(transmission);
            return true;
        });
    }

    /**
     *
     * @param transmission
     * @return
     */
    @Override
    public boolean delete(Transmission transmission) {
        return this.tx(session -> {
            session.delete(transmission);
            return true;
        });
    }


    /**
     *
     * @param transmission
     * @return
     */
    @Override
    public int add(Transmission transmission) {
        return this.tx(session -> {
            session.save(transmission);
            return transmission.getTransId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Transmission get(int id) {
        return this.tx(session -> (Transmission) session.get(Transmission.class, id));

    }

    @Override
    public Transmission getByName(String name) {
        return this.tx(session -> {
            Transmission transmission = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.Transmission WHERE tr_name =:name");
            query.setParameter("name", name);
            List<Transmission> transmissionsList = query.getResultList();
            if (!transmissionsList.isEmpty()) {
                transmission = transmissionsList.get(0);
            }
            return transmission;
        });
    }

    /**
     *
     * @return
     */
    @Override
    public List<Transmission> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.entities.Transmission").list());
    }

    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }


}
