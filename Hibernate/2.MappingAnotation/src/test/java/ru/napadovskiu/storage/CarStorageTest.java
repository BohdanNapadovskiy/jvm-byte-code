package ru.napadovskiu.storage;

import org.junit.Before;
import org.junit.Test;
import ru.napadovskiu.entities.Car;
import ru.napadovskiu.entities.Engine;
import ru.napadovskiu.entities.GearBox;
import ru.napadovskiu.entities.Transmission;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarStorageTest {

    private CarStorage store = CarStorage.getInstance();

    private EngineStorage engineStorage = EngineStorage.getInstance();

    private GearBoxStorage gearBoxStorage = GearBoxStorage.getInstance();

    private TransStorage transStorage = TransStorage.getInstance();

    private int idEngine;

    private int idGearBox;

    private int idTransm;


    @Before
    public void beforeTest() {
        Engine engine = new Engine();
        engine.setEngineName("Электрический");
        this.idEngine = engineStorage.add(engine);

        GearBox gearBox = new GearBox();
        gearBox.setGearBoxName("Роторная");
        this.idGearBox = gearBoxStorage.add(gearBox);

        Transmission trans = new Transmission();
        trans.setTransName("Комбинированная");
        this.idTransm = transStorage.add(trans);

    }

    @Test
    public void whenAddCarrThenReturnId() {
        Car car = new Car();
        car.setCarName("Фольксваген");
        car.setEngine(new Engine(this.idEngine));
        car.setGearBox(new GearBox(this.idGearBox));
        car.setTransmission(new Transmission(this.idTransm));
        int idCar = store.add(car);

        Car tmpCar = new Car(idCar);
        assertThat(idCar, is(tmpCar.getCarId()));


    }

    @Test
    public void whenUpdateThenReturnTrue() {
        Car car = new Car();
        car.setCarName("Мерседес");
        boolean result = store.update(car);
        assertThat(result, is(true));

    }

    @Test
    public void whenDeleteThenReturnTrue() {
        Car car = new Car();
        car.setCarName("Ауди");

        boolean result = store.delete(car);

        Car tmpCar = new Car(idEngine);
        assertThat(result, is(true));


    }

}