package ru.napadovskiu.entities;

/**
 *
 */
public class Images {

    /**
     *
     */
    private int imageId;

    /**
     *
     */
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
