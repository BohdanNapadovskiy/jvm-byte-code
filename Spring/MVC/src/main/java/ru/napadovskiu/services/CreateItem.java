package ru.napadovskiu.services;

import org.apache.log4j.Logger;
import ru.napadovskiu.entities.*;
import ru.napadovskiu.storage.*;

import java.sql.Timestamp;

public class CreateItem {


    private static final Logger LOGGER = Logger.getLogger(CreateItem.class);

    /**
     *
     */
    private static final CreateItem INSTANCE = new CreateItem();


    /**
     *
     */
    private CreateItem() {
    }

    /**
     *
     * @return
     */
    public static final CreateItem getInstance() {
        return INSTANCE;
    }



    /**
     *
     */
    private final ItemStorage itemStorage = ItemStorage.getInstance();

    /**
     *
     */
    private final CarStorage carStorage = CarStorage.getInstance();

    /**
     *
     */
    private final EngineStorage engineStorage = EngineStorage.getInstance();

    /**
     *
     */
    private final TransStorage transStorage = TransStorage.getInstance();

    /**
     *
     */
    private final GearBoxStorage gearBoxStorage = GearBoxStorage.getInstance();

    /**
     *
     */
    private final ImageStorage imageStorage = ImageStorage.getInstance();


    /**
     *
     * @param transName
     * @return
     */
    private Transmission getTransmission(String transName) {
        Transmission transmission = transStorage.getByName(transName);
        if (transmission == null) {
            Transmission trans = new Transmission();
            trans.setTransName(transName);
            transStorage.add(trans);
            transmission = transStorage.getByName(trans.getTransName());
        }

        return transmission;
    }

    private GearBox getGearBox(String gearBoxName) {
        GearBox gearBox = gearBoxStorage.getByName(gearBoxName);
        if (gearBox == null) {
            GearBox tmpGearBox = new GearBox();
            tmpGearBox.setGearBoxName(gearBoxName);
            gearBoxStorage.add(tmpGearBox);
            gearBox = gearBoxStorage.getByName(tmpGearBox.getGearBoxName());
        }

        return gearBox;
    }

    /**
     *
     * @param engineName
     * @return
     */
    private Engine getEngine(String engineName) {
        Engine engine = engineStorage.getByName(engineName);
        if (engine == null) {
            Engine tmpEngine = new Engine();
            tmpEngine.setEngineName(engineName);
            engineStorage.add(tmpEngine);
            engine = engineStorage.getByName(engineName);
        }

        return engine;
    }

    /**
     *
     * @param path
     * @return
     */
    private Images getImages(String path) {
        Images tmpImage = new Images();
        tmpImage.setImagePath(path);
        imageStorage.add(tmpImage);
        Images image = imageStorage.getByName(path);

        return image;
    }

    /**
     *
     * @param carName
     * @param gearBox
     * @param trans
     * @param engine
     * @param images
     * @return
     */
    private Car getCar(String carName, GearBox gearBox, Transmission trans, Engine engine, Images images) {
        Car newCar = new Car();
        newCar.setCarName(carName);
        newCar.setGearBox(gearBox);
        newCar.setTransmission(trans);
        newCar.setEngine(engine);
        newCar.setImages(images);

        return newCar;
    }


    /**
     *
     * @param carName
     * @param gearBoxName
     * @param transName
     * @param engineName
     * @param pathFile
     * @return
     */
    public Item createItem(String carName, String gearBoxName, String transName, String engineName, String pathFile) {
        Item newItem = null;
        Transmission transmission = getTransmission(transName);
        GearBox gearBox = getGearBox(gearBoxName);
        Engine engine = getEngine(engineName);
        Images image = getImages(pathFile);
        Car newCar = getCar(carName, gearBox, transmission, engine, image);

        carStorage.add(newCar);

        newItem = new Item();
        newItem.setDescription(carName);
        newItem.setCar(newCar);
        newItem.setDate(new Timestamp(System.currentTimeMillis()));

        itemStorage.add(newItem);

        return newItem;
    }

    /**
     *
     * @param editItem
     * @param editCar
     * @param carName
     * @param gearBoxName
     * @param transName
     * @param engineName
     * @param user
     */
    public void editItem(Item editItem, Car editCar,String carName, String gearBoxName, String transName, String engineName,  User user) {
        Transmission transmission = getTransmission(transName);
        GearBox gearBox = getGearBox(gearBoxName);
        Engine engine = getEngine(engineName);

        editCar.setEngine(engine);
        editCar.setTransmission(transmission);
        editCar.setGearBox(gearBox);
        editCar.setCarName(carName);

        editItem.setDescription(carName);
        editItem.setUser(user);
        itemStorage.update(editItem);

    }
}
