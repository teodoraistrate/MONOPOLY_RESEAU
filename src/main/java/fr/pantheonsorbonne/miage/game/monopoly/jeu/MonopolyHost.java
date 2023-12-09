package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Random;

/**
 * Cet exemple représente l'hôte dans le jeu Monopoly.
 */
public final class MonopolyHost {

    private MonopolyHost() {
    }

    public static void main(String[] args) throws Exception {
        PlayerFacade playerFacade = (PlayerFacade) Facade.getFacade();
        HostFacade hostFacade = (HostFacade) Facade.getFacade();
        hostFacade.waitReady();
        // Définir le nom de notre joueur
        playerFacade.createNewPlayer("Nicolas Herbaut le meilleur prof de POO" + new Random().nextInt());

        Plateau monopoly = Plateau.getInstance();

        /*while (true) {
            Game game = hostFacade.createNewGame("monopoly");
            hostFacade.waitForExtraPlayerCount(3); // Par exemple, attendre 3 joueurs
            playTheGame(playerFacade, game);
        }*/

        Game game = hostFacade.createNewGame("monopoly");
        hostFacade.waitForExtraPlayerCount(3); // Par exemple, attendre 3 joueurs

        while (true) {
            //get a command from someone else
            GameCommand commandLoop = hostFacade.receiveGameCommand(game);

            switch (commandLoop.name()) {
                case "enCours":
                    //if we receive a new board, the game is not other and we should player one more round
                    playTheGame(playerFacade, game, monopoly);
                    break;
                case "gameOver":
                    //if the game is other, stop playing and show the results
                    handleGameOver(playerFacade, game, monopoly);
                    System.exit(0);
            }


        }
    }

    private static void playTheGame(PlayerFacade playerFacade, Game game, Plateau monopoly) {

        // tant que c'est true le jeu continu 
        while (true) {
            // Vérifier si le jeu est terminé
            if (handleGameOver(playerFacade, game, monopoly))
                break;

            // Effectuer des actions de jeu Monopoly ici
            // (par exemple, les joueurs jouent tour à tour, se déplacent sur le plateau, gèrent les transactions de propriété, etc.)

            // Envoyer l'état de jeu mis à jour à tous les joueurs
            playerFacade.sendGameCommandToAll(game, new GameCommand("board", monopoly.getBoardState()));

            // Attendre les commandes des joueurs et mettre à jour l'état du jeu en conséquence
            // (par exemple, gérer les mouvements des joueurs, les achats de propriétés, etc.)
            GameCommand command = playerFacade.receiveGameCommand(game);
            // Mettre à jour l'état du jeu Monopoly en fonction de la commande du joueur

            // Répéter la boucle pour le tour suivant ou jusqu'à ce que le jeu se termine
        }


        while(true) {
            
        }
    }

    private static boolean handleGameOver(PlayerFacade playerFacade, Game game, Plateau monopoly) {
        // Vérifier si le jeu est terminé en fonction des règles du Monopoly
        // (par exemple, toutes les propriétés sont détenues par un joueur, faillites, etc.)

        // Si le jeu est terminé, envoyer un message de fin de jeu approprié et l'état du plateau à tous les joueurs
        // Par exemple :
        playerFacade.sendGameCommandToAll(game, new GameCommand("gameover", "winner"));
        playerFacade.sendGameCommandToAll(game, new GameCommand("board", monopoly.getBoardState()));

        // Afficher le résultat du jeu (par exemple, gagnant, perdant, égalité) dans la console
        System.out.println("Fin du jeu ! Résultat : ...");

        // Renvoyer true si le jeu est terminé, false sinon
        return false;
    }
}
