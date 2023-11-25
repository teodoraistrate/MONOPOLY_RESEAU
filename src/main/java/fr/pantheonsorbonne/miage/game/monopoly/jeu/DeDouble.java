package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Prison;

public class DeDouble {
    private int valeurDe;
    private static final int VALEUR_MAX = 12;
    protected int resultatDe;
    private boolean memeValeur;

    public DeDouble() {
        this.resultatDe = 0;
        this.valeurDe = 0;
        this.memeValeur = false;
    }

    public int getValeur() {
        return this.valeurDe;
    }

    public boolean memeValeur() {
        return memeValeur;
    }

    public int resultatDe() {
        return resultatDe;
    }
    

    public void lancerDes() {
        //lancer deux dés
        int de1 = (int) (Math.random() * 6) + 1;  // Valeur entre 1 et 6
        int de2 = (int) (Math.random() * 6) + 1;

        // résultat et la valeur du dé
        resultatDe = de1 + de2;
        valeurDe = Math.min(resultatDe, VALEUR_MAX);

        // si deux dés ont la mm valeur
        memeValeur = de1 == de2;
    }


    public void memeValeurDes() {
        int nbLancersDoublesConsecutifs = 0; // Compteur de dés doubles consécutifs
        for (int i = 0; i < 3; i++) {
            lancerDes();
            // si les dés ont la même valeur
            if (memeValeur()) {
                nbLancersDoublesConsecutifs++;
            } else {
                nbLancersDoublesConsecutifs = 0; // Réinitialise le compteur s'il n'y a pas de doubles
            }
        }
        // Si le joueur a obtenu des dés doubles pendant trois lancers consécutifs
        if (nbLancersDoublesConsecutifs == 3 && memeValeur()) {
            Prison.conditionPourAllerEnPrison(null, nbLancersDoublesConsecutifs, memeValeur, memeValeur, memeValeur); // Le joueur va en prison
        }
    }
   
    /* 
    public void avancerJoueur(Joueur joueur) {
        //position du joueur en fonction du résultat des dés
        joueur.avancer(resultatDe);
    };*/
    //on sait pas quoi faire avec 
}


/*Dans dedouble faut faire : 
 *modeliser le resultat des deux de pour faire avancer le joueur
 *si les deux dé ont la meme valeur on fait rejouer le joueur
 *si meme dédoubles 3 fois, go en prison
 */