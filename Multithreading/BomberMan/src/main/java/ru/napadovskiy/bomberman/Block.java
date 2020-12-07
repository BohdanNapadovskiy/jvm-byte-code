package ru.napadovskiy.bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Package of Multithreading test task.
 * Class block.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.10.2017

 */
public class Block extends Unit {

    /**
     * Constructor for class.
     * @param board game board.
     */
    public Block(int x, int y, GameBoard board, ExecutorService service) {
        super(x, y, board, service);

    }


    /**
     * Override method for check coordinate.
     * @param x coordinate.
     * @param y coordinate.
     * @return result.
     */
    @Override
    boolean checkCoordinate(int x, int y) {
        return false;
    }

    /**
     *Override method for run class.
     *
     */
    @Override
    public void run() {
        while (!getService().isShutdown()) {
            this.getBoard().getBoard()[this.getXCoordinate()][this.getYCoordinate()] = new ReentrantLock();
            if (this.getBoard().getBoard()[this.getXCoordinate()][this.getYCoordinate()].tryLock()) {
                this.getBoard().getBoard()[this.getXCoordinate()][this.getYCoordinate()].lock();
            }
        }
    }
}
