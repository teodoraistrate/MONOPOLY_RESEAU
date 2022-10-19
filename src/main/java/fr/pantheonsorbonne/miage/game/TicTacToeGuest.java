package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.PlayerFacadePorcelain;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Collection;
import java.util.Random;

public class TicTacToeGuest {
    public static void main(String[] args) throws Exception, FullBoardException {
        PlayerFacadePorcelain facade = PlayerFacadePorcelain.getFacade();
        facade.waitReady();
        facade.createNewPlayer("Nicolas-" + new Random().nextInt());
        Collection<Game> availableChifoumiGames;

        Game currentGame = facade.autoJoinGame("tictactoe");


        GameCommand command = facade.receiveGameCommand(currentGame);
        if (!command.name().equals("youare")) {
            throw new RuntimeException();
        }
        char myMark = command.body().charAt(0);
        TicTacToe board = null;
        while (true) {
            GameCommand commandLoop = facade.receiveGameCommand(currentGame);
            switch (commandLoop.name()) {
                case "board":
                    board = new TicTacToe(commandLoop.body());
                    board.addRand(myMark);
                    facade.sendGameCommand(currentGame, new GameCommand("board", board.toFlatString()));
                    break;
                case "gameover":
                    TicTacToe finalChifoumi = new TicTacToe(facade.receiveGameCommand(currentGame).body());
                    switch (commandLoop.body()) {
                        case "victory":
                            System.out.println("I've won\n " + finalChifoumi);
                            break;
                        case "defeat":
                            System.out.println("I've lost\n" + finalChifoumi);
                            break;
                        case "tie":
                            System.out.println("It's a tie\n" + finalChifoumi);
                    }
                    System.exit(0);
            }


        }


    }

}





