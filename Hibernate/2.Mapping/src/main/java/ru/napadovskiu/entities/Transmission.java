package ru.napadovskiu.entities;

public class Transmission {


    /**
     *
     */
    private int transId;

    /**
     *
     */
    private String transName;

    /**
     *
     */
    public Transmission() {
    }

    /**
     *
     * @param transId
     */
    public Transmission(int transId) {
        this.transId = transId;
    }


    public Transmission(int transId, String transName) {
        this.transId = transId;
        this.transName = transName;
    }

    /**
     *
     * @return
     */
    public int getTransId() {
        return transId;
    }

    /**
     *
     * @return
     */
    public String getTransName() {
        return transName;
    }

    /**
     *
     * @param transId
     */
    public void setTransId(int transId) {
        this.transId = transId;
    }

    /**
     *
     * @param transName
     */
    public void setTransName(String transName) {
        this.transName = transName;
    }

}
