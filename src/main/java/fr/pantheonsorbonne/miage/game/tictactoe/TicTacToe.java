package fr.pantheonsorbonne.miage.game.tictactoe;

public interface TicTacToe {
    /**
     * put a new mark on the board
     *
     * @param c
     * @throws FullBoardException
     */
    void addRand(char c) throws FullBoardException;

    /**
     * tells if the board is full
     *
     * @return true if the board is full, false otherwhise
     */
    boolean isFull();

    /**
     * converts the board to a flat string (e.g. XXXOOOX.O)
     *
     * @return the flat string
     */
    String toFlatString();

    @Override
    String toString();

    /**
     * the the mark of the next player that needs to play
     *
     * @return the mark of the next player
     */
    char nextPlayer();

    /**
     * get the winner of the game
     *
     * @return 'X' if X won, 'O' if O won, '.' is no one won yet
     */
    char getWinner();
}
