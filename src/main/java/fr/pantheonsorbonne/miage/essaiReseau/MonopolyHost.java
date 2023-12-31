package fr.pantheonsorbonne.miage.essaiReseau;

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
    private static List<Joueur> listeJoueursR = new ArrayList<>();

    private final HostFacade hostFacade;
    private final Game game;

    private MonopolyHost(HostFacade h, Game g) {
        this.hostFacade = h;
        this.game = g;
    }
 
    public static MonopolyHost getInstance() {
        return instance;
    }

    @Override
    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public static void main(String[] args) {

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();
        // Définir le nom de l'hote
        hostFacade.createNewPlayer("Host");

        // Création du jeu
        Game game = hostFacade.createNewGame("Monopoly");
        hostFacade.waitForExtraPlayerCount(2); // Par exemple, attendre 2 joueurs

        // Définition de la liste des Joueurs
        Set<String> setJoueurs = game.getPlayers();
        
        for (String nomJoueur : setJoueurs) {
            listeJoueursR.add(new JoueurS1(nomJoueur));
        }

        MonopolyHost jeu = new MonopolyHost(hostFacade, game);
        instance = jeu;

        jeu.jouerMonopoly(listeJoueursR);

    }


    @Override
    protected boolean choixSortirPrison(String idJoueur) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askGetOutOfJail",
                null, null));

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("YesOut");
    }

    @Override
    protected boolean choixAcheterPropriete(String idJoueur, Propriete propriete) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askBuyProperty",
                propriete.getName(), null)); 

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("YesBuy");
    }

    @Override
    protected boolean choixPayerOuAttendre(String idJoueur, Terrain proprieteSquatee) {
        hostFacade.sendGameCommandToPlayer(game, idJoueur, new GameCommand("askRemoveInstantlySquat",
            proprieteSquatee.getName(), null)); 

        GameCommand reponse = hostFacade.receiveGameCommand(game);

        return reponse.name().equals("YesGetRid");
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
