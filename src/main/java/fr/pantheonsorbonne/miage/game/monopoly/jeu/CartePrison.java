package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public class CartePrison extends Carte{

    protected CartePrison(String description) {
        super(description);
    }

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
        Prison prison = new 
    }

}
