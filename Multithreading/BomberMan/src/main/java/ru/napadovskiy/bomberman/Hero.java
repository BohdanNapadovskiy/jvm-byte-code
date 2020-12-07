package ru.napadovskiy.bomberman;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Package of Multithreading test task.
 * Class hero extends abstract class unit.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.10.2017
 */
public class Hero extends Unit implements Runnable {

    /**
     *
     * @param x
     * @param y
     * @param board
     * @param service
     */
    public Hero(int x, int y, GameBoard board, ExecutorService service) {
        super(x, y, board, service);
    }


    /**
     *
     * @param x
     * @param y
     */
    public void clearPosition(int x, int y) {
        if (this.getBoard().getBoard()[x][y].tryLock()) {
            this.getBoard().getBoard()[x][y].unlock();
            this.getBoard().getBoard()[x][y] = new ReentrantLock();
        }

    }


    /**
     *
     * @param oldX
     * @param direction
     * @return
     */
    public int getNewXCoordinate(int oldX, Direction direction) {
        int result = oldX;
        if (direction.equals(Direction.LEFT)) {
            result--;
        } else if (direction.equals(Direction.RIGHT)) {
            result++;
        }

        return result;
    }

    /**
     *
     * @param oldY
     * @param direction
     * @return
     */
    private int getNewYCoordinate(int oldY, Direction direction) {
        int result = oldY;
        if (direction.equals(Direction.DOWN)) {
            result--;
        } else if (direction.equals(Direction.UP)) {
            result++;
        }

        return result;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override

    boolean checkCoordinate(int x, int y) {
        boolean result = false;

        if ((x >= 0 && x < this.getBoard().getXSize()) && ((y >= 0 && y < this.getBoard().getYSize()))) {
            result = true;
        }

        return result;
     }

    /**
     *
     * @return
     */
    private Direction getDirection() {
        Direction result = null;

        String text = "";
        new ConsoleInput().askUser(text);
        if (text.equals("s"))  {
            result = Direction.DOWN;
        } else if (text.equals("w")) {
            result = Direction.UP;
        } else if (text.equals("a")) {
            result = Direction.LEFT;
        } else if (text.equals("d")) {
            result = Direction.RIGHT;
        }

        return  result;

     }


    /**
     * Method move the hero.
     * @throws InterruptedException exception.
     */
    private void moveHero() throws InterruptedException {


        ReentrantLock newCell = null;

        Direction direction = getDirection();

        int newX = getNewXCoordinate(this.getXCoordinate(), direction);

        int newY = getNewYCoordinate(this.getYCoordinate(), direction);

        if (checkCoordinate(newX, newY)) {
            newCell = this.getBoard().getBoard()[newX][newY];
            if (newCell == null) {
                newCell = new ReentrantLock();
                this.getBoard().getBoard()[newX][newY] = new ReentrantLock();
            }

            clearPosition(this.getXCoordinate(), this.getYCoordinate());

            newCell.lock();
            this.setXCoordinate(newX);
            this.setYCoordinate(newY);
        }

    }

    @Override
    public void run() {
        this.getBoard().getBoard()[this.getXCoordinate()][this.getYCoordinate()] = new ReentrantLock();
        while (!getService().isShutdown()) {
            try {
                this.moveHero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
