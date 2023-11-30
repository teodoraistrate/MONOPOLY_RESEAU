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
    static List<Joueur> listeJoueurs = new ArrayList<>();

    public static List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    /*public static void removeJoueur(Joueur joueur) {
        listeJoueurs.remove(Joueur joueur);
    }*/

    public static void initialiserListeJoueurs() {
        JoueurS1 joueur1 = new JoueurS1("Joueur 1");
        JoueurS2 joueur2 = new JoueurS2("Joueur 2");
        listeJoueurs.add(joueur1);
        listeJoueurs.add(joueur2);
    }
    
    public static void main(String[] args) {

        Prison prison = Prison.getInstance("Prison");

        int nombreTours = 0;

        initialiserListeJoueurs();

        // le jeu s'arrête quand il reste un seul joueur
        while (listeJoueurs.size()>1) {
            for (Joueur joueur : listeJoueurs) {
                boolean lancerDes = true; 
                // on a ajouté cette variable pour qu'un joueur puisse lancer les dés plusieurs fois si c'est la même valeur
                int nombreFoisMemeValeur = 0;
                // le but de cette variable est de mettre le joueur en prison s'il a la même valeur 3 fois
                while(lancerDes) {
                    DeDouble des = new DeDouble();
                    des.lancerDes();
                    if (!des.memeValeur()) {
                        lancerDes = false; // si les deux dés n'ont pas la même valeur alors il ne peut lancer les dés qu'une seule fois
                    }
                    else {
                        nombreFoisMemeValeur++;
                    }
                    if (nombreFoisMemeValeur == 3) {
                        prison.mettreJoueurEnPrison(joueur);
                    }
                    joueur.deplacerNombreCases(des.resultatDe(), true);
                    
                }
            }
            nombreTours++;
        }

    }

}
