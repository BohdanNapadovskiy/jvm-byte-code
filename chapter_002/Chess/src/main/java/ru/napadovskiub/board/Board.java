package ru.napadovskiub.board;

import ru.napadovskiub.figure.Figure;
import ru.napadovskiub.figure.Elephant;
import ru.napadovskiub.figure.Horse;
import ru.napadovskiub.figure.King;
import ru.napadovskiub.figure.Pawn;
import ru.napadovskiub.figure.Rook;
import ru.napadovskiub.figure.Queen;


/**
 *Class board for chess
 *@author napadovskiy
 *@since 07.08.2016
 *@version 1
 */
public class Board {
    /**
     *Max size of board.
     */
    private final  int size = 8;

    /**
     *Chess board.
     */
    private final Figure[][] board = new Figure[size][size];

    /**
     *Constructor class.
     * @return board.
     */
    public Figure[][] getBoard() {
        return this.board;
    }

    /**
     *Method fills the board with pawns.
     * @param isWhite white or not pawn.
     */
    public void fillPawn(boolean isWhite) {
        int firstLine = 1;

        final int firstLineForBlack = 6;

        if (!isWhite) {
            firstLine = firstLineForBlack;
        }
        for (int i = 0; i < size; i++) {
            Figure pawn = new Pawn(firstLine, i, isWhite);
            this.setFigureByPosition(pawn);
        }
    }

    /**
     *Method fills the board with figure.
     * @param isWhite white or not pawn.
     */
    public void fillFigure(boolean isWhite) {
        int firstLine = 0;
        final int firstLineForBlack = 7;
        if (!isWhite) {
            firstLine = firstLineForBlack;
        }
        this.setFigureByPosition(new Rook(firstLine, 0, isWhite));
        this.setFigureByPosition(new Rook(firstLine, 7, isWhite));
        this.setFigureByPosition(new Horse(firstLine, 1, isWhite));
        this.setFigureByPosition(new Horse(firstLine, 6, isWhite));
        this.setFigureByPosition(new Elephant(firstLine, 2, isWhite));
        this.setFigureByPosition(new Elephant(firstLine, 5, isWhite));
        this.setFigureByPosition(new Queen(firstLine, 3, isWhite));
        this.setFigureByPosition(new King(firstLine, 4, isWhite));
    }

    /**
     *Method fills the board with figure.
     * @param isWhite white or not pawn.
     */
    public void fillBoard(boolean isWhite) {
        this.fillPawn(isWhite);
        this.fillFigure(isWhite);
    }

    /**
     *Method return figure.
     *@param y coordinate y.
     *@param x coordinate x.
     *@return figure with coordinate.
     */
    public Figure getFigureByPosition(int y, int x) {
        return  this.board[y][x];
    }

    /**
     *Method clear position of the board.
     *@param y coordinate y.
     *@param x coordinate x.
     */
    public void clearPosition(int y, int x) {
        this.board[y][x] = null;
    }

    /**
     *Method sets the figure to the position of board.
     *@param figure figure of chess.
     */
    public void setFigureByPosition(Figure figure) {
        this.board[figure.getPositionY()][figure.getPositionX()] = figure;

    }

    /**
     *Method clears the position on the board.
     *@param figure chess figure.
     *@param positionY position y.
     *@param positionX position x.
     *@return result.
     */
    private boolean occupiedCell(Figure figure, int positionY, int positionX) {
        boolean result = true;
        if (!(figure instanceof Horse)) {
            if (this.getFigureByPosition(positionY, positionX) != null) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Method takes shape.
     * @param newFigure new figure.
     * @param newY new y.
     * @param newX new x.
     */
    private void takeShape(Figure newFigure, int newY, int newX) {
        if (this.getFigureByPosition(newY, newX) != null) {
           if (newFigure.getColorFigure() != this.getFigureByPosition(newY, newX).getColorFigure()) {
               clearPosition(newY, newX);
           }
        }
    }

    /**
     *Method chek color of figure on the new position.
     * @param figure figure.
     * @param y coordinate y.
     * @param x coordinate x.
     * @return result.
     */
    private boolean checkNewPosition(Figure figure, int y, int x) {
        boolean result = false;
        if (this.getFigureByPosition(y, x) != null && figure.getColorFigure() != this.getFigureByPosition(y, x).getColorFigure()) {
            result = true;
        } else if (this.getFigureByPosition(y, x) == null) {
            result = true;
        }
        return result;
    }

    /**
     *Method checks the possibility of move.
     *@param figure figure.
     *@param newY new coordinate y.
     *@param newX new coordinate x.
     * @return result.
     */
    private boolean canMoveFigure(Figure figure, int newY, int newX) {
        boolean result = false;
        Position[] allAvailableMoves = figure.getAvailableMoves();
        if (checkNewPosition(figure, newY, newX)) {

            for (int i = 0; i < allAvailableMoves.length; i++) {
                if (allAvailableMoves[i] == null) {
                    continue;
                }
                if (!occupiedCell(figure, allAvailableMoves[i].getPositionY(), allAvailableMoves[i].getPositionX())) {
                    result = false;
                    break;
                }
                if (allAvailableMoves[i].getPositionY() == newY && allAvailableMoves[i].getPositionX() == newX) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     *Method move figure on the board.
     *@param figure figure.
     *@param newPositionY new coordinate y.
     *@param newPositionX new coordinate x.
     */
    public void moveFigure(Figure figure, int newPositionY, int newPositionX) {

        if (this.canMoveFigure(figure, newPositionY, newPositionX)) {
            clearPosition(figure.getPositionY(), figure.getPositionX());

            takeShape(figure, newPositionY, newPositionX);

            figure.setPosition(newPositionY, newPositionX);

            setFigureByPosition(figure);
        } else {
            System.out.println("Inaccessible move");
        }
    }
}