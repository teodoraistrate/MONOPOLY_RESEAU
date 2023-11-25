package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public abstract class CarteRecevoir extends Carte {

    protected CarteRecevoir(String description) {
        super(description);
    }

    protected abstract int montantARecevoir();

    @Override
    public void appliquerEffet(Joueur joueur) {
        joueur.ajouterArgent(this.montantARecevoir());
    }
    
}
