package ru.napadovskiu.storage;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.napadovskiu.entities.Images;

import java.util.List;
import java.util.function.Function;

public class ImageStorage implements Storage<Images>{
    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(ImageStorage.class);


    /**
     *
     */
    private static final ImageStorage INSTANCE = new ImageStorage();


    /**
     *
     */
    private ImageStorage() {
    }

    /**
     *
     * @return
     */
    public static ImageStorage getInstance() {
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
     * @param images
     * @return
     */
    @Override
    public boolean update(Images images) {
        return this.tx(session -> {
            session.save(images);
            return true;
        });
    }


    /**
     *
     * @param images
     * @return
     */
    @Override
    public boolean delete(Images images) {
        return this.tx(session -> {
            session.delete(images);
            return true;
        });
    }


    /**
     *
     * @param images
     * @return
     */
    @Override
    public int add(Images images) {
        return this.tx(session -> {
            session.save(images);
            return images.getImageId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Images get(int id) {
        return this.tx(session -> (Images) session.get(Images.class, id));

    }

    @Override
    public Images getByName(String path) {
        return this.tx(session -> {
            Images image = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.Images WHERE path =:path");
            query.setParameter("path", path);
            List<Images> imageList = query.getResultList();
            if (!imageList.isEmpty()) {
                image = imageList.get(0);
            }
            return image;
        });

    }

    /**
     *
     * @return
     */
    @Override
    public List<Images> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.entities.Images").list());
    }


    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }



}
