package ru.napadovskiu.storage;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.napadovskiu.entities.Car;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class CarStorage implements Storage<Car> {

    private static final Logger LOGGER = Logger.getLogger(CarStorage.class);

    /**
     *
     */
    private static final CarStorage INSTANCE = new CarStorage();


    /**
     *
     */
    private CarStorage() {
    }

    /**
     *
     * @return
     */
    public static CarStorage getInstance() {
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
     * @param car
     * @return
     */
    @Override
    public boolean update(Car car) {
        return this.tx(session -> {
            session.save(car);
            return true;
        });
    }


    /**
     *
     * @param car
     * @return
     */
    @Override
    public boolean delete(Car car) {
        return this.tx(session -> {
            session.delete(car);
            return true;
        });
    }

    /**
     *
     * @param car
     * @return
     */
    @Override
    public int add(Car car) {
        return this.tx(session -> {
            session.save(car);
            return car.getCarId();
        });
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Car get(int id) {
        return this.tx(session -> (Car) session.get(Car.class, id));

    }


    @Override
    public Car getByName(String name) {
        return this.tx(session -> {
            Car car = null;
            Query query = session.createQuery("FROM ru.napadovskiu.entities.Car WHERE car_name =:name");
            query.setParameter("name", name);
            List<Car> carList = query.getResultList();
            if (!carList.isEmpty()) {
                car = carList.get(0);
            }
            return car;
        });
    }


    /**
     *
     * @return
     */
    @Override
    public List<Car> getAll() {
        return this.tx(session -> session.createQuery("from ru.napadovskiu.entities.Car").list());
    }


    public List<Car> getAllCarName() {
        return this.tx(session -> session.createQuery("select car.carName from ru.napadovskiu.entities.Car car").list());
    }


    /**
     *
     */
    @Override
    public void close() {
        HibernateFactory.getInstance().close();
    }


}
