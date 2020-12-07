package ru.napadovskiy.bomberman;

import java.util.concurrent.ExecutorService;

/**
 * Package of Multithreading test task.
 * abstract class for all unit in game.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.10.2017
 */
public abstract class Unit implements  Runnable {

    /**
     * x coordinate.
     */
    private int xCoordinate;

    /**
     * y coordinate.
     */
    private int yCoordinate;

    /**
     * board for game.
     */
    private final GameBoard board;

    /**
     *
     */
    private final ExecutorService service;

    /**
     * Constructor for all unit.
     * @param board board for game.
     */
    public Unit(final int x, final int y, final GameBoard board, final ExecutorService service) {
        this.board  = board;
        this.service = service;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.service.execute(this);
    }

    /**
     * Method return x coordinate.
     * @return x coordinate.
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Method return y coordinate.
     * @return y coordinate.
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Method set x coordinate.
     * @param xCoordinate x coordinate.
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Method set y coordinate.
     * @param yCoordinate y coordinate.
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Method return board for game.
     * @return board.
     */
    public GameBoard getBoard() {
        return this.board;
    }

    /**
     *
     * @return
     */
    public ExecutorService getService() {
        return this.service;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    abstract boolean checkCoordinate(int x, int y);

}
