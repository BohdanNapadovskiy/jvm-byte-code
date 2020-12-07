package ru.napadovskiu.entities;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "images")
public class Images {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false, updatable = false)
    private int imageId;

    /**
     *
     */
    @Column(name = "path")
    private String imagePath;

    /**
     *
     */
    public Images() {
    }

    /**
     *
     * @param imageId
     */
    public Images(int imageId) {
        this.imageId = imageId;
    }

    /**
     *
     * @return
     */
    public int getImageId() {
        return imageId;
    }

    /**
     *
     * @param imageId
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     *
     * @return
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     *
     * @param imagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
