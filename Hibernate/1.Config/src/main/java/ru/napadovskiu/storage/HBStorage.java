package ru.napadovskiu.storage;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.napadovskiu.models.Items;

import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class HBStorage implements Storage<Items> {

    /**
     *
     */
    private static final HBStorage INSTANCE = new HBStorage();


    /**
     *
     */
    public HBStorage() {
    }

    /**
     *
     * @return
     */
    public static HBStorage getInstance() {
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
     * @param items
     * @return
     */
    @Override
    public boolean update(Items items) {
        return this.tx(session -> {
            session.save(items);
            return true;
        });
    }

    /**
     *
     * @param items
     * @return
     */
    @Override
    public boolean delete(Items items) {
        return this.tx(session -> {
            session.delete(items);
            return true;
        });
    }

    /**
     *
     * @param items
     * @return
     */
    @Override
    public int add(Items items) {
        return this.tx(session -> {
            session.save(items);
            return items.getId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Items get(int id) {
        return this.tx(session -> (Items) session.get(Items.class, id));

    }

    /**
     *
     * @return
     */
    @Override
    public List<Items> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.models.Items").list() );
    }

    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }


}
