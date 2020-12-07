package ru.napadovskiub.figure;

import ru.napadovskiub.board.*;

/**
 * Created by Napadovskiy
 *@author napadovskiy
 *@since 14.10.2016
 *@version 1
 */
public class Pawn implements Figure {

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
    public Pawn(int y, int x, boolean isWhite) {
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
        Position[] result = new Position[2];
        int counter = 0;

        int tmpX = this.getPositionX();
        int tmpY = this.getPositionY();

        if ((this.position.getPositionY() == 1 && this.isWhite) || (this.getPositionY() == 6 && !this.isWhite)) {
            result[counter++] = new Position(++tmpY, tmpX);
            result[counter++] = new Position(++tmpY, tmpX);
        } else {
            result[counter++] = new Position(++tmpY, tmpX);
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

