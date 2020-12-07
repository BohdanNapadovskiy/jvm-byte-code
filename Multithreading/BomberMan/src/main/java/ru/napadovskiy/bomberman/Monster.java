package ru.napadovskiy.bomberman;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Package of Multithreading test task.
 * Class monster extends from unit.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.10.2017
 */
public class Monster extends Unit implements Runnable {


    /**
     * Constructor for all unit.
     * @param board board for game.
     */
    public Monster(int x, int y, GameBoard board, ExecutorService service) {
         super(x, y, board, service);

   }

    /**
     *
     * @param x
     * @param direction
     * @return
     */
    private int calculateNewXCoordinate(int x, Direction direction) {
        int result = x;
        if (direction.equals(Direction.LEFT)) {
            result--;
        } else if (direction.equals(Direction.RIGHT)) {
            result++;
        }

        return result;

    }

    /**
     *
     * @param oldy
     * @param direction
     * @return
     */
    private int calculateNewYCoordinate(int oldy, Direction direction) {
        int result = oldy;
        if (direction.equals(Direction.DOWN)) {
            result--;
        } else if (direction.equals(Direction.UP)) {
            result++;
        }
        return result;
    }

    /**
     *
     * @return direction
     */
    private Direction getRandomDirection() {
        Direction result = null;
        Random random = new Random();
        int randomCount = random.nextInt(4);
        if (randomCount == 0) {
            result = Direction.DOWN;
        } else if (randomCount == 1) {
            result = Direction.LEFT;
        } else if (randomCount == 2) {
            result = Direction.RIGHT;
        } else if (randomCount == 3) {
            result = Direction.UP;
        }
        return result;
    }

    /**
     * Method clear position.
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
     * Method move monster in new position.
     * @throws InterruptedException
     */
    private void moveMonster() throws InterruptedException {

        int newX = this.getXCoordinate();

        int newY = this.getYCoordinate();

        final int waitTime = 5;

        boolean canMove = false;

        ReentrantLock newCell = null;

        while (!canMove) {
            Direction direction = getRandomDirection();

            newX = calculateNewXCoordinate(this.getXCoordinate(), direction);

            newY = calculateNewYCoordinate(this.getYCoordinate(), direction);

            if (checkCoordinate(newX, newY)) {
                newCell = this.getBoard().getBoard()[newX][newY];
                if (newCell == null) {
                    newCell = new ReentrantLock();
                    this.getBoard().getBoard()[newX][newY] = new ReentrantLock();
                }
                if (newCell.tryLock(waitTime, TimeUnit.SECONDS)) {

                    canMove = true;
                }
            }
        }
        clearPosition(this.getXCoordinate(), this.getYCoordinate());

        newCell.lock();
        this.setXCoordinate(newX);
        this.setYCoordinate(newY);

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
     * Method for start move hero.
     */
    @Override
    public void run() {
        this.getBoard().getBoard()[this.getXCoordinate()][this.getYCoordinate()] = new ReentrantLock();
        while (!getService().isShutdown()) {
            try {
                moveMonster();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
