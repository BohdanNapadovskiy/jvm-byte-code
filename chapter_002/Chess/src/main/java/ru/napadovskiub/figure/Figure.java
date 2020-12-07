package ru.napadovskiub.figure;

import ru.napadovskiub.board.Position;

/**
 *
 */
public interface Figure {

    /**
     *
     * @param y
     * @param x
     */
    void setPosition(int y, int x);

    /**
     *
     * @param isWhite
     */
    void setColorFigure(boolean isWhite);

    /**
     *
     * @return
     */
    int getPositionY();

    /**
     *
     * @return
     */
    int getPositionX();

    /**
     *
     * @return
     */
    boolean getColorFigure();

    /**
     *
     * @return
     */
    Position[] getAvailableMoves();

}