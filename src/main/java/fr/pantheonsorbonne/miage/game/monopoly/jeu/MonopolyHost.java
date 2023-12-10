package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurReseau;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Cet exemple représente l'hôte dans le jeu Monopoly.
 */
public final class MonopolyHost extends JeuMonopoly {

    private static MonopolyHost instance;
    Prison prison = Prison.getInstance("Prison");
    private static List<Joueur> listeJoueurs = new ArrayList<>();

    private final HostFacade hostFacade;
    private final Game game;

    private MonopolyHost(HostFacade h, Game g) {
        this.hostFacade = h;
        this.game = g;
    }
 
    public static MonopolyHost getInstance() {
        return instance;
    }
/* 
    private static void initialiserListeJoueurs(Set<String> setJoueurs) {
        int joueurCount = 0;
        for (String nomJoueur : setJoueurs) {
            switch (joueurCount) {
                case 0:
                    listeJoueurs.add(new JoueurReseau(nomJoueur, playerFacade, game, "S1"));
                    break;
                case 1:
                    listeJoueurs.add(new JoueurReseau(nomJoueur, playerFacade, game, "S2"));
                    break;
            }
            joueurCount++;
        }
    }
*/
    @Override
    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public static void main(String[] args) {

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();
        // Définir le nom de l'hote
        hostFacade.createNewPlayer("Nicolas Herbaut le meilleur prof de POO - host");

        // Création du jeu
        Game game = hostFacade.createNewGame("Monopoly");
        hostFacade.waitForExtraPlayerCount(2); // Par exemple, attendre 2 joueurs

        // Définition de la liste des Joueurs
        Set<String> setJoueurs = game.getPlayers();
        //initialiserListeJoueurs(setJoueurs);
        int joueurCount = 0;
        for (String nomJoueur : setJoueurs) {
            switch (joueurCount) {
                case 0:
                    listeJoueurs.add(new JoueurReseau(nomJoueur, "S1"));
                    break;
                case 1:
                    listeJoueurs.add(new JoueurReseau(nomJoueur, "S2"));
                    break;
            }
            joueurCount++;
        }

        MonopolyHost jeu = new MonopolyHost(hostFacade, game);
        instance = jeu;

        jeu.jouerMonopoly();

    }


    @Override
    protected boolean choixSortirPrison(String idJoueur) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askGetOutOfJail",
                null, null));

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("yesOut");
    }

    @Override
    protected boolean choixAcheterPropriete(String idJoueur, Propriete propriete) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askBuyProperty",
                propriete.getName(), null)); 

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("yesBuy");
    }

    @Override
    protected boolean choixPayerOuAttendre(String idJoueur, Terrain proprieteSquatee) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askRemoveInstantlySquat",
            proprieteSquatee.getName(), null)); 

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("yesGetRid");
    }

    @Override
    protected void thinkAndDo(String idJoueur) {
        // à implémenter
    }
    @Override
    protected void declarerFinJeu() {
        for (Joueur j : listeJoueurs) {
            hostFacade.sendGameCommandToPlayer(game, j.getName(), new GameCommand("gameOver",
            null, null)); 
        }
    }

}
