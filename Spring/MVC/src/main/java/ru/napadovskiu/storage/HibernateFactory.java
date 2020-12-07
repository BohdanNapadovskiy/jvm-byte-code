package ru.napadovskiu.storage;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 */
public class HibernateFactory {



    /**
     *
     */
   private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            SESSION_FACTORY = configuration
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     *
     * @return
     */
    public static SessionFactory getInstance() {
        return SESSION_FACTORY;
    }
}
