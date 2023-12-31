package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;

public abstract class Carte {
    public String description;

    protected Carte(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException;
}