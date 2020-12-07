package ru.napadovskiyb;

/**
 * Package of CollectionPro finalTask.
 * Class for order have info about order.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 01.08.2017
 */
public class Order  {

    /**
     * price of order.
     */
    private double price;

    /**
     * order volume.
     */
    private int volume;

    /**
     * order id.
     */
    private int idOrder;

    /**
     * operation order True - buy, False -sell.
     */
    private boolean typeOperation;


    /**
     * Constructor for class.
     * @param price price order.
     * @param volume vo;ume order.
     * @param idOrder id order.
     * @param typeOperation type of operation buy-true, sell-false.
     */
    public Order(boolean typeOperation, double price, int volume, int idOrder) {
        this.price = price;
        this.volume = volume;
        this.idOrder = idOrder;
        this.typeOperation = typeOperation;
    }

    /**
     * Method return price order.
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method return volume order.
     * @return volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Method return id order.
     * @return id order.
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * Method return type of operation.
     * True is buy, False - sell.
     * @return type of order.
     */
    public boolean isTypeOperation() {
        return typeOperation;
    }

    /**
     * Method set price order.
     * @param price of order.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method set volume order.
     * @param volume volume order.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Method set id order.
     * @param idOrder order.
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * Method set type of order.
     * @param typeOperation order.
     */
    public void setTypeOperation(boolean typeOperation) {
        this.typeOperation = typeOperation;
    }

}
