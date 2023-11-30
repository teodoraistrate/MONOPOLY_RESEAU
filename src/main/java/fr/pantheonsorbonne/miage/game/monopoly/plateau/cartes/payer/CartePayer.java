package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public abstract class CartePayer extends Carte {

    protected CartePayer(String description) {
        super(description);
    }

    public abstract int getMontantAPayer(Joueur joueur);

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException{
        if (joueur.getPorteMonnaie()<this.getMontantAPayer(joueur)) {
            joueur.declarerPerte();
            throw new PasAssezArgentException("Vous n'avez pas assez d'argent pour payer, donc vous avez perdu!");
        }
        joueur.payer(this.getMontantAPayer(joueur));
    }
    
}