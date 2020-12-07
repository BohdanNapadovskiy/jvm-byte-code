package ru.napadovskiy;

/**
 * Package of Multithreading treads.
 *
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 25.09.2017
 * @param <E> generic.
 */
public final class Model<E> {

    /**
     * version for check.
     */
    private volatile int version;

    /**
     * value.
     */
    private E  value;

    /**
     * Constructor for class with params.
     * @param value value of Model.
     */

   public Model(E value) {
       this.value = value;
   }

    /**
     * Method return version of model.
     * @return version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Method set version.
     * @param version version.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Method return value of model.
     * @return value.
     */
    public E getValue() {
        return value;
    }

    /**
     * Method set value for model.
     * @param value value.
     */
    public void setValue(E value) {
        this.value = value;
    }

}
