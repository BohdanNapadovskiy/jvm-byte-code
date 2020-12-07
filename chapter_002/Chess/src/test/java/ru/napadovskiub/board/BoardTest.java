package ru.napadovskiub.board;

import  ru.napadovskiub.figure.*;

import static org.hamcrest.core.Is.is;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class BoardTest {

    /**
     *
     */
    @Test
    public void whenMoveWhiteRookIsCorrectThenMoveFigure() {
        Board board  = new Board();
        Figure whiteRook = new Rook(2, 2, true);
        board.setFigureByPosition(whiteRook);
        board.moveFigure(whiteRook, 7, 2);
        board.moveFigure(whiteRook, 7, 5);
        assertThat(board.getFigureByPosition(7, 5), is(whiteRook));

    }

    /**
     *
     */
    @Test
    public void whenMoveWhiteRookIsUnCorrectThenDontMove() {
        Board board  = new Board();
        Figure whiteRook = new Rook(2, 2, true);
        board.setFigureByPosition(whiteRook);
        Figure blackPawn = new Pawn(4, 2, false);
        board.setFigureByPosition(blackPawn);
        board.moveFigure(whiteRook, 7, 2);
        assertThat(board.getFigureByPosition(2, 2), is(whiteRook));
    }

    /**
     *
     */
    @Test
    public void whenMoveWhiteElephantIsCorrect() {
        Board board  = new Board();
        Figure whiteElephant = new Elephant(0, 1, true);
        board.setFigureByPosition(whiteElephant);
        board.moveFigure(whiteElephant, 4, 5);
        assertThat(board.getFigureByPosition(4, 5), is(whiteElephant));

    }

    /**
     *
     */
    @Test
    public void whenMoveWhiteHorseIsCorrect() {
        Board board  = new Board();
        Figure whiteHorse = new Horse(2, 2, true);
        board.setFigureByPosition(whiteHorse);
        board.moveFigure(whiteHorse, 3, 3);
        assertThat(board.getFigureByPosition(2, 2), is(whiteHorse));
    }

    /**
     *
      */
    @Test
    public void whenMoveWhiteHorseIsUnCorrectThenDontMove() {
        Board board  = new Board();
        Figure whiteHorse = new Horse(2, 2, true);
        board.setFigureByPosition(whiteHorse);
        Figure blackPawn = new Pawn(4, 1, false);
        board.setFigureByPosition(blackPawn);
        board.moveFigure(whiteHorse, 4, 1);
        assertThat(board.getFigureByPosition(4, 1), is(whiteHorse));
    }

    /**
     *
     */
    @Test
    public void whenMoveWhiteKingIsCorrect() {
        Board board  = new Board();
        Figure whiteKing = new King(2, 2, true);
        board.setFigureByPosition(whiteKing);
        board.moveFigure(whiteKing, 1, 1);
        assertThat(board.getFigureByPosition(1, 1), is(whiteKing));

    }

    /**
     *
     */
    @Test
    public void whenMoveWhiteQueenIsCorrect() {
        Board board  = new Board();
        Figure whiteHorse = new Queen(3, 3, true);
        board.setFigureByPosition(whiteHorse);
        board.moveFigure(whiteHorse, 6, 0);
        assertThat(board.getFigureByPosition(6, 0), is(whiteHorse));

    }

}
