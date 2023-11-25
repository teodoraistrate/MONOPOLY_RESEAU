package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class Gare extends Propriete {

    private int nombreGaresJoueur = 0;

    public Gare(String name, int price) {
        super(name, price);
    }

    // getteurs
    public int getNombreGaresJoueur() {
        return nombreGaresJoueur;
    }

    public void compterNombreGaresProprietaire() {
        Joueur proprietaire = this.getProprietaire();
        for (Propriete propriete : proprietaire.getProperties()) {
            if (propriete instanceof Gare) {
                nombreGaresJoueur++;
            }
        }
    }

    @Override
    public int getLoyer() {
        return 25*nombreGaresJoueur;
    }
    
}
