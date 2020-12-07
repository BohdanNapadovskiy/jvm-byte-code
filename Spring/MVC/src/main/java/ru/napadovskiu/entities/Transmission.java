package ru.napadovskiu.entities;

import javax.persistence.*;

@Entity
@Table(name = "transmission")
public class Transmission {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id", nullable = false, updatable = false)
    private int transId;

    /**
     *
     */
    @Column(name = "tr_name")
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
