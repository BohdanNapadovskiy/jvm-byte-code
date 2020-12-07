package ru.napadovskiub.figure;

import ru.napadovskiub.board.*;

/**
 * Created by Napadovskiy
 *@author napadovskiy
 *@since 14.10.2016
 *@version 1
 */
public class Elephant implements Figure {

    /**
     *
     */
    private Position position;

    /**
     *
     */
    private boolean isWhite;

    /**
     *
     * @param y
     * @param x
     * @param isWhite
     */
    public Elephant(int y, int x, boolean isWhite) {
        this.position = new Position(y, x);
        this.isWhite   =  isWhite;
    }

    /**
     *The method get array of all available moves
     *@return an array of all moves
     */
    public Position[] getAvailableMoves() {
         Position[] result = new Position[16];


        int counter = 0;

         int tmpY = this.getPositionY();
         int tmpX = this.getPositionX();
         while (tmpY < 7 && tmpX < 7) {
             result[counter] = new Position(++tmpY, ++tmpX);
             counter++;
         }

         tmpY = this.getPositionY();
         tmpX = this.getPositionX();
         while (tmpX > 0 && tmpY < 7) {
             result[counter] = new Position(++tmpY, --tmpX);
             counter++;
         }
        tmpY = this.getPositionY();
        tmpX = this.getPositionX();
         while (tmpX < 7 && tmpY > 0) {
            result[counter] = new Position(--tmpY, ++tmpX);
            counter++;

         }
        tmpY = this.getPositionY();
        tmpX = this.getPositionX();
        while (tmpX > 0 && tmpY > 0) {
            result[counter] = new Position(--tmpY,  --tmpX);
            counter++;
        }

        return result;
     }

    /**
     *The method set position
     */
    public void setPosition(int y, int x) {
        this.position = new Position(y, x);
    }

    /**
     *The method set color
     */
    public  void setColorFigure(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     *The method get position Y
     */
    public int getPositionY() {
        return this.position.getPositionY();

    }

    /**
     *The method get position X
     */
    public int getPositionX() {
        return this.position.getPositionX();

    }

    /**
     *The method get color figure
     */
    public boolean getColorFigure() {
        return this.isWhite;
    }
}

