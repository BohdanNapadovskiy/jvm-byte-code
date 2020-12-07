package ru.napadovskiu.entities;


import javax.persistence.*;

@Entity
@Table(name = "gearbox")
public class GearBox {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gear_id", nullable = false, updatable = false)
    private int gearBoxId;

    /**
     *
     */
    @Column(name = "gear_name")
    private String gearBoxName;

    /**
     *
     */
    public GearBox() {
    }

    /**
     *
     * @param gearBoxId
     */
    public GearBox(int gearBoxId) {
        this.gearBoxId = gearBoxId;
    }


    public GearBox(int gearBoxId, String gearBoxName) {
        this.gearBoxId = gearBoxId;
        this.gearBoxName = gearBoxName;
    }

    /**
     *
     * @param gearBoxId
     */
    public void setGearBoxId(int gearBoxId) {
        this.gearBoxId = gearBoxId;
    }

    /**
     *
     * @param gearBoxName
     */
    public void setGearBoxName(String gearBoxName) {
        this.gearBoxName = gearBoxName;
    }


    /**
     *
     * @return
     */
    public int getGearBoxId() {
        return gearBoxId;
    }

    /**
     *
     * @return
     */
    public String getGearBoxName() {
        return gearBoxName;
    }

}
