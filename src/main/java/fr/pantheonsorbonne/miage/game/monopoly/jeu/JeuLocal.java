package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS2;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;

public class JeuLocal {

    public static final Plateau plateau = Plateau.getInstance();
    Prison prison = Prison.getInstance("Prison");
    static List<Joueur> listeJoueurs = new ArrayList<>();

    public static void initialiserListeJoueurs() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        JoueurS2 joueur2 = new JoueurS2("Joueur 2");
        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur2);
    }
    
    public static void main(String[] args) {

        int nombreTours = 0;

        initialiserListeJoueurs();

        // le jeu s'arrête quand il reste un seul joueur
        while (listeJoueurs.size()>1) {
            
        }

    }

}
