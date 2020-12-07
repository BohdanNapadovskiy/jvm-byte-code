package ru.napadovskiub.figure;

import ru.napadovskiub.board.*;


/**
 * Created by Napadovskiy
 *@author napadovskiy
 *@since 14.10.2016
 *@version 1
 */
public class King implements Figure {
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
    public King(int y, int x, boolean isWhite) {
        this.position = new Position(y, x);
        this.isWhite   = isWhite;
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
     *The method get array of all available moves
     *@return an array of all moves
     */
    public Position[] getAvailableMoves() {
        Position[] result = new Position[8];
        int counter = 0;

        int tmpX = this.getPositionX();
        int tmpY = this.getPositionY();
        if (tmpY > 0) {
            result[counter] = new Position(tmpY - 1, tmpX);
            counter++;
        }
        if (tmpY > 0 && tmpX > 0) {
            result[counter] = new Position(tmpY - 1, tmpX - 1);
            counter++;
        }
        if (tmpX < 7 && tmpY > 0) {
            result[counter] = new Position(tmpY - 1, tmpX + 1);
            counter++;
        }

        if (tmpX > 0) {
            result[counter] = new Position(tmpY, tmpX - 1);
            counter++;
        }
        if (tmpX < 7) {
            result[counter] = new Position(tmpY, tmpX + 1);
            counter++;
        }
        if (tmpY < 7) {
            result[counter] = new Position(tmpY + 1, tmpX);
            counter++;
        }
        if (tmpY < 7 && tmpX < 7) {
            result[counter] = new Position(tmpY + 1, tmpX + 1);
            counter++;
        }
        if (tmpX > 0 && tmpY < 7) {
            result[counter] = new Position(tmpY + 1, tmpX - 1);
            counter++;
        }


        return result;
    }

    /**
     *The method get color figure
     */
    public boolean getColorFigure() {
        return this.isWhite;
    }


}

