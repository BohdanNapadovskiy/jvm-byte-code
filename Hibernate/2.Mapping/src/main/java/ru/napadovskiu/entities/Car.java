package ru.napadovskiu.entities;



public class Car {

    /**
     *
     */
    private int carId;

    /**
     *
     */
    private String carName;

    /**
     *
     */
    private Engine engine;

    /**
     *
     */
    private GearBox gearBox;

    /**
     *
     */
    private Images images;

    /**
     *
     */
    private Transmission transmission;

    /**
     *
     */
    public Car() {

    }

    /**
     *
     * @param carId
     */
    public Car(int carId) {
        this.carId = carId;
    }

    /**
     *
     * @return
     */
    public int getCarId() {
        return carId;
    }

    /**
     *
     * @param carId
     * @param carName
     * @param engine
     * @param gearBox
     * @param images
     * @param transmission
     */
    public Car(int carId, String carName, Engine engine, GearBox gearBox, Images images, Transmission transmission) {
        this.carId = carId;
        this.carName = carName;
        this.engine = engine;
        this.gearBox = gearBox;
        this.images = images;
        this.transmission = transmission;
    }

    /**
     *
     * @return
     */
    public String getCarName() {
        return carName;
    }

    /**
     *
     * @return
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     *
     * @return
     */
    public GearBox getGearBox() {
        return gearBox;
    }

    /**
     *
     * @return
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     *
     * @param carId
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     *
     * @param carName
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     *
     * @param engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     *
     * @param gearBox
     */
    public void setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
    }

    /**
     *
     * @param transmission
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     *
     * @return
     */
    public Images getImages() {
        return this.images;
    }

    /**
     *
     * @param images
     */
    public void setImages(Images images) {
        this.images = images;
    }

}
