package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public abstract class CarteAvancer extends Carte {

    protected CarteAvancer(String description) {
        super(description);
    }

    public abstract int getNouvellePosition(Joueur joueur);

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException {
        int nouvellePosition = this.getNouvellePosition(joueur);
        joueur.deplacerNombreCases(nouvellePosition, true);
    }

}
