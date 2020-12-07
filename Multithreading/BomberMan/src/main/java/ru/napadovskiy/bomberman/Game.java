package ru.napadovskiy.bomberman;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Package of Multithreading.
 * Main class start game.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 10.10.2017
 */
public class Game {


    /**
     * Count of monsters.
     */
    private int countOfMonsters;

    /**
     * Count of block.
     */
    private int countOfBlocks;

    /**
     * x size of Board.
     */
    private int xSizeBoard;

    /**
     *x size of Board.
     */
    private int ySizeBoard;

    /**
     * Game board.
     */
    private final GameBoard board;

    /**
     * Executor service.
     */
    private static ExecutorService service = Executors.newCachedThreadPool();

    /**
     * Constructor for class.
     * @param x x size.
     * @param y y size.
     * @param countOfMonsters count of monster.
     * @param countOfBlocks count of blocks.
     */
    private Game(int x, int y, int countOfMonsters, int countOfBlocks) {
        this.xSizeBoard = x;
        this.ySizeBoard = y;
        this.countOfMonsters = countOfMonsters;
        this.countOfBlocks = countOfBlocks;
        this.board = new GameBoard(x, y);
    }


    /**
     * Method return random x coordinate.
     * @return x coordinate.
     */
    private int getRandomXCoordinate() {
        Random random = new Random();
        int randomCount = random.nextInt(this.xSizeBoard);

        return randomCount;

    }

    /**
     * Method return random y coordinate.
     * @return y coordinate.
     */
    private int getRandomYCoordinate() {
        Random random = new Random();
        int randomCount = random.nextInt(this.ySizeBoard);

        return randomCount;

    }

    /**
     * Method add hero in game.
     */
    private void addHero() {
        if ((this.board.getBoard()[0][0] == null) || (this.board.getBoard()[0][0].tryLock())) {
            Hero hero = new Hero(0, 0, board, service);
        }
    }

    /**
     * Method adds monsters in game.
     */
    private void addMonster() {
        for (int i = 0; i < this.countOfMonsters; i++) {
            int x = getRandomXCoordinate();
            int y = getRandomYCoordinate();
            Monster monster = new Monster(x, y, board, service);
        }
    }

    /**
     * Method add blocks in game.
     */
    private void addBlock() {
        for (int i = 0; i < this.countOfBlocks; i++) {
            int x = getRandomXCoordinate();
            int y = getRandomYCoordinate();
            Block block = new Block(x, y, board, service);

        }
    }


    /**
     * Method start game.
     */
    private void initGame() {
        addBlock();
        addHero();
        addMonster();

    }


    /**
     * Main method for game.
     * @param args array string.
     */
    public static void main(String[] args) {

        final int x = 8;

        final int y = 8;

        final int countOfMonster = 2;

        final int countOfBlocks = 2;

        Game game = new Game(x, y, countOfMonster, countOfBlocks);
        game.initGame();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }

}
