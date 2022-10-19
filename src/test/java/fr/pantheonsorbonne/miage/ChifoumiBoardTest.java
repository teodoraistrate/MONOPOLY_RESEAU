package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.TicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChifoumiBoardTest {

    @Test
    public void testWinner1() {
        TicTacToe board = new TicTacToe("XXXOXOXXO");
        assertEquals("XXX\nOXO\nXXO", board.toString());
        assertEquals('X', board.getWinner());
    }

    @Test
    public void testWinner2() {
        TicTacToe board = new TicTacToe("XXOOOOXOX");
        assertEquals("XXO\nOOO\nXOX", board.toString());
        assertEquals('O', board.getWinner());
    }

    @Test
    public void testWinner3() {
        TicTacToe board = new TicTacToe("XOOOXXOXX");
        assertEquals("XOO\nOXX\nOXX", board.toString());
        assertEquals('X', board.getWinner());
    }

    @Test
    public void testWinner4() {
        TicTacToe board = new TicTacToe("XOOXOXOXX");

        assertEquals('O', board.getWinner());
    }

    @Test
    public void testNextPlayer() {
        TicTacToe board = new TicTacToe("X...");

        assertEquals('O', board.nextPlayer());
    }

    @Test
    public void testNextPlayer2() {
        TicTacToe board = new TicTacToe("XXO.");

        assertEquals('O', board.nextPlayer());
    }


}