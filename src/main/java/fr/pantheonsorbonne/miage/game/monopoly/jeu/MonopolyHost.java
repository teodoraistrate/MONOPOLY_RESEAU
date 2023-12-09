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
    private static List<Joueur> listeJoueurs;

    private MonopolyHost() {
    }

    public static MonopolyHost getInstance() {
        if (instance == null) {
            instance = new MonopolyHost();
        }
        return instance;
    }

    private static void initialiserListeJoueurs(Set<String> setJoueurs) {
        int joueurCount = 0;
        for (String nomJoueur : setJoueurs) {
            switch (joueurCount) {
                case 0:
                    listeJoueurs.add(new JoueurS1(nomJoueur));
                    break;
                case 1:
                    listeJoueurs.add(new JoueurS2(nomJoueur));
                    break;
            }
            joueurCount++;
        }
    }

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
        initialiserListeJoueurs(setJoueurs);

    }


    @Override
    protected boolean askGetOutOfJail(String idJoueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askGetOutOfJail'");
    }

    @Override
    protected boolean askBuyProperty(String idJoueur, Propriete propriete) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askBuyProperty'");
    }

    @Override
    protected boolean askRemoveInstantlySquat(String idJoueur, Terrain proprieteSquatee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askRemoveInstantlySquat'");
    }

    @Override
    protected void thinkAndDo(String idJoueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAndDo'");
    }

}
