package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.tictactoe.FullBoardException;
import fr.pantheonsorbonne.miage.game.tictactoe.TicTacToe;
import fr.pantheonsorbonne.miage.game.tictactoe.TicTacToeImpl;

/**
 * this is an example for a stand-alone offline game
 */
public class TicTacToeStandAlone {
    public static void main(String... args) throws FullBoardException {
        //create a new board
        TicTacToe board = new TicTacToeImpl(10);

        //while the game is not over (no winner and board not full)
        while (board.getWinner() == '.' && !board.isFull()) {
            //get the mark of the next player
            char next = board.nextPlayer();
            //and play it
            board.addRand(next);
        }

        // the game is over, who is the winner?
        char winner = board.getWinner();
        //there is a winner
        if (winner != '.') {
            System.out.println("Winner is " + winner);
        } else {//its a tie
            System.out.println("Tie");
        }
        //print the final board
        System.out.println(board);

    }
}
