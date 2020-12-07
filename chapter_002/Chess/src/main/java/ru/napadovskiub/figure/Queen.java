package ru.napadovskiub.figure;

import ru.napadovskiub.board.Position;

/**
 * Created by Napadovskiy
 *@author napadovskiy
 *@since 14.10.2016
 *@version 1
 */
public class Queen implements Figure {

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
    public Queen(int y, int x, boolean isWhite) {
        this.position = new Position(y, x);
        this.isWhite   = isWhite;
    }

    /**
     *The method get array of all available moves to line
     */
    private Position[] getAvailableMovesByLine() {
        Position[] result = new Position[16];

        int tmpY = this.getPositionY();
        int tmpX = this.getPositionX();
        int counter = 0;
        boolean fillMinus = false;
        boolean fillPlus = false;

        for (; counter < result.length;) {
            if (tmpX >= 7) {
                tmpX = this.getPositionX();
                break;
            }
            if (this.getPositionX() > 0 && !fillMinus) {
                result[counter] = new Position(tmpY, --tmpX);
                counter++;
                fillMinus = (tmpX == 0) ? true : false;
                tmpX = (tmpX == 0) ? this.getPositionX() : tmpX;
                continue;
            }

            if (!fillPlus) {
                result[counter] = new Position(tmpY, ++tmpX);
                counter++;
                fillPlus = (tmpX == 7) ? true : false;

            }
        }
        fillMinus = false;
        fillPlus = false;
        for (; counter < result.length;) {
            if (tmpY >= 7) {
                tmpY = this.getPositionY();
                break;
            }
            if (this.getPositionY() > 0 && !fillMinus) {
                result[counter] = new Position(--tmpY, tmpX);
                counter++;
                fillMinus = (tmpY == 0) ? true : false;
                tmpY = (tmpY == 0) ? this.getPositionY() : tmpY;
                continue;
            }
            if (!fillPlus) {
                result[counter] = new Position(++tmpY, tmpX);
                counter++;
                fillPlus = (tmpY == 7) ? true : false;

            }
        }

        return result;
    }

    private Position[] getAvailableMovesByDiagonal() {
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
            result[counter] = new Position(--tmpY, --tmpX);
            counter++;
        }

        return result;
    }

    /**
     *The method check opportunity move the figure
     *@return an array of all moves
     */
    public Position[] getAvailableMoves() {
        Position[] result = new Position[32];
        //Position[] availableMoves  = new Position[32];

        Position[] availableMovesByLine = getAvailableMovesByLine();
        Position[] availableMovesByDiagonal = getAvailableMovesByDiagonal();

        System.arraycopy(availableMovesByLine, 0, result, 0, availableMovesByLine.length);
        System.arraycopy(availableMovesByDiagonal, 0, result, availableMovesByLine.length, availableMovesByDiagonal.length);

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
