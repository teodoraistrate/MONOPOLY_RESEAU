package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public abstract class CarteRecevoir extends Carte {

    protected CarteRecevoir(String description) {
        super(description);
    }

    protected abstract int montantARecevoir();

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException{
        joueur.ajouterArgent(this.montantARecevoir());
        if (this instanceof CarteRecevoirJoueurs) {
            CarteRecevoirJoueurs carteRecevoir = (CarteRecevoirJoueurs) this;
            carteRecevoir.joueursDonnentArgent(joueur);
        }
    }
    
}
