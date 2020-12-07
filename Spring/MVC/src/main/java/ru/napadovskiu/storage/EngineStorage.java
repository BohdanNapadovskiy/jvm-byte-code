package ru.napadovskiu.storage;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.napadovskiu.entities.Engine;

import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class EngineStorage implements Storage<Engine> {

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(EngineStorage.class);


    /**
     *
     */
    private static final EngineStorage INSTANCE = new EngineStorage();


    /**
     *
     */
    private EngineStorage() {
    }

    /**
     *
     * @return
     */
    public static EngineStorage getInstance() {
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
     * @param engine
     * @return
     */
    @Override
    public boolean update(Engine engine) {
        return this.tx(session -> {
            session.update(engine);
            return true;
        });
    }


    /**
     *
     * @param engine
     * @return
     */
    @Override
    public boolean delete(Engine engine) {
        return this.tx(session -> {
            session.delete(engine);
            return true;
        });
    }

    /**
     *
     * @param engine
     * @return
     */
    @Override
    public int add(Engine engine) {
        return this.tx(session -> {
            session.save(engine);
            return engine.getEngineId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Engine get(int id) {
        return this.tx(session -> (Engine) session.get(Engine.class, id));

    }

    @Override
    public Engine getByName(String name) {
        return this.tx(session -> {
            Engine engine = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.Engine WHERE eng_name =:name");
            query.setParameter("name", name);
            List<Engine> engineList = query.getResultList();
            if (!engineList.isEmpty()) {
                engine = engineList.get(0);
            }
            return engine;
        });
    }

    /**
     *
     * @return
     */
    @Override
    public List<Engine> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.entities.Engine").list());
    }

    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }


}
