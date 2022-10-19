/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.PlayerFacadeImpl;
import fr.pantheonsorbonne.miage.PlayerFacadePorcelain;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Random;

/**
 * Main class that boot the Camel application
 */
public final class TicTacToeHost {

    private TicTacToeHost() {
    }

    public static void main(String[] args) throws Exception, FullBoardException {
        PlayerFacadePorcelain facade = PlayerFacadeImpl.getSingleton();
        facade.waitReady();

        facade.createNewPlayer("Nicolas" + new Random().nextInt());

        tictactoeloop:
        while (true) {
            Game game = facade.createNewGame("tictactoe");
            facade.waitForPlayerCount(2);

            TicTacToe board = new TicTacToe(6);
            char myMark = 'X';
            facade.sendGameCommand(game, new GameCommand("youare", "O"));
            while (true) {
                if (board.getWinner() == myMark) {
                    facade.sendGameCommand(game, new GameCommand("gameover", "defeat"));
                    facade.sendGameCommand(game, new GameCommand("board", board.toFlatString()));
                    System.out.println("victory!\n" + board);
                    continue tictactoeloop;
                } else if (board.getWinner() == 'O') {
                    facade.sendGameCommand(game, new GameCommand("gameover", "victory"));
                    facade.sendGameCommand(game, new GameCommand("board", board.toFlatString()));
                    System.out.println("defeat!\n" + board);
                    continue tictactoeloop;
                } else if (board.isFull()) {
                    facade.sendGameCommand(game, new GameCommand("gameover", "tie"));
                    facade.sendGameCommand(game, new GameCommand("board", board.toFlatString()));
                    System.out.println("tie!\n" + board);
                    continue tictactoeloop;
                }

                board.addRand(myMark);
                facade.sendGameCommand(game, new GameCommand("board", board.toFlatString()));
                GameCommand command = facade.receiveGameCommand(game);
                if (!command.name().equals("board")) {
                    throw new RuntimeException("should be a board command now");
                }

                board = new TicTacToe(command.body());
            }

        }
    }

}
