package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.tictactoe.TicTacToe;
import fr.pantheonsorbonne.miage.game.tictactoe.AbstractTicTacToe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChifoumiBoardTest {

    @Test
    public void testWinner1() {
        TicTacToe board = new AbstractTicTacToe("XXXOXOXXO");
        assertEquals("XXX\nOXO\nXXO", board.toString());
        assertEquals('X', board.getWinner());
    }

    @Test
    public void testWinner2() {
        TicTacToe board = new AbstractTicTacToe("XXOOOOXOX");
        assertEquals("XXO\nOOO\nXOX", board.toString());
        assertEquals('O', board.getWinner());
    }

    @Test
    public void testWinner3() {
        TicTacToe board = new AbstractTicTacToe("XOOOXXOXX");
        assertEquals("XOO\nOXX\nOXX", board.toString());
        assertEquals('X', board.getWinner());
    }

    @Test
    public void testWinner4() {
        TicTacToe board = new AbstractTicTacToe("XOOXOXOXX");

        assertEquals('O', board.getWinner());
    }

    @Test
    public void testNextPlayer() {
        TicTacToe board = new AbstractTicTacToe("X...");

        assertEquals('O', board.nextPlayer());
    }

    @Test
    public void testNextPlayer2() {
        TicTacToe board = new AbstractTicTacToe("XXO.");

        assertEquals('O', board.nextPlayer());
    }


}