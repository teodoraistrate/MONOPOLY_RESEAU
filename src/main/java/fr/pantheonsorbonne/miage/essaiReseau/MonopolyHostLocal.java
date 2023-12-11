package fr.pantheonsorbonne.miage.essaiReseau;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS3;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Propriete;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Terrain;

public class MonopolyHostLocal extends JeuMonopoly {

    private static MonopolyHostLocal instance;
    List<Joueur> listeJoueurs;

    public MonopolyHostLocal() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        JoueurS2 joueur2 = new JoueurS2("Joueur 2");

        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur2);
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

    public static void main(String[] args) {
        MonopolyHostLocal jeu = new MonopolyHostLocal();
        jeu.jouerMonopoly(jeu.listeJoueurs);
    }

    @Override
    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }
  
    @Override
    protected boolean choixSortirPrison(String idJoueur) {
        Joueur joueur = getJoueurParId(idJoueur);
        return joueur.choixSortirPrison();
    }

    @Override
    protected boolean choixAcheterPropriete(String idJoueur, Propriete propriete) {
        Joueur joueur = getJoueurParId(idJoueur);
        return joueur.choixAcheterPropriete(propriete);
    }

    @Override
    protected boolean choixPayerOuAttendre(String idJoueur, Terrain proprieteSquatee) {
        Joueur joueur = getJoueurParId(idJoueur);
        return joueur.choixPayerOuAttendre();
    }

    @Override
    protected void thinkAndDo(String idJoueur) {
        Joueur joueur = getJoueurParId(idJoueur);

        // Logique pour la réflexion et l'action du joueur
        List<Propriete> proprietesAchete = joueur.choixProprietesARacheter();
        for (Propriete p : proprietesAchete) {
            boolean aAchete = choixAcheterPropriete(idJoueur, p);
            if (aAchete) {
                try {
                    joueur.acheterPropriete(p);
                } catch (PasAssezArgentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void declarerFinJeu() {
        // rien ne se passe en plus - on a declaré la victoire dans la classe JeuMonopoly
    }


}
