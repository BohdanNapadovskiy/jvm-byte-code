package ru.napadovskiub.board;

/**
 * Created by program on 24.10.2016.
 */
public class Position {
    private int positionY;
    private int positionX;

    /**
     *
     * @param y
     * @param x
     */
    public Position(int y, int x) {
        this.positionY = y;
        this.positionX = x;
    }

    /**
     *
     * @return
     */
    public int getPositionY() {
        return this.positionY;
    }

    /**
     *
     * @return
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     *
     * @param y
     */
    public void setPositionY(int y) {
        this.positionY = y;
    }

    /**
     *
     * @param x
     */
    public void  setPositionX(int x) {
        this.positionX = x;
    }

}
