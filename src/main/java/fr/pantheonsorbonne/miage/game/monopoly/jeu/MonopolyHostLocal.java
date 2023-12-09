package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS3;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class MonopolyHostLocal extends JeuMonopoly {

    private static MonopolyHostLocal instance;
    List<Joueur> listeJoueurs = new ArrayList<>();

    public MonopolyHostLocal() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        JoueurS2 joueur2 = new JoueurS2("Joueur 2");
        JoueurS3 joueur3 = new JoueurS3("Joueur 3");

        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur2);
        listeJoueurs.add(joueur3);
    }

    public static MonopolyHostLocal getInstance() {
        if (instance == null) {
            instance = new MonopolyHostLocal();
        }
        return instance;
    }

    public Joueur getJoueurParId(String idJoueur) {
        for (Joueur j : listeJoueurs) {
            if (j.getName().equals(idJoueur)) return j;
        }
        return null;
    }

    @Override
    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
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
        return proprieteSquatee.getProprietaire().choixPayerOuAttendre();
    }

    @Override
    protected void thinkAndDo(String idJoueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAndDo'");
    }

    

}
