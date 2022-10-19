package fr.pantheonsorbonne.miage.game;

public class TicTacToeStandAlone {
    public static void main(String ...args) throws FullBoardException {
        TicTacToe board = new TicTacToe(10);

        while(board.getWinner()=='.' && !board.isFull()){
            char next=board.nextPlayer();
            board.addRand(next);
        }

        char winner=board.getWinner();
        if(winner!='.'){
            System.out.println("Winner is "+winner);
        }
        else{
            System.out.println("Tie");
        }
        System.out.println(board);

    }
}
