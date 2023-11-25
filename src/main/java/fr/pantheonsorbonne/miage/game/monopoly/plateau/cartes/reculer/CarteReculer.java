package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public abstract class CarteReculer extends Carte {

    protected CarteReculer(String description) {
        super(description);
    }

    public abstract int getNouvellePosition(Joueur joueur) throws NomPasValideException;

    @Override
    public void appliquerEffet(Joueur joueur) throws NomPasValideException{
        try {
            joueur.deplacerSurPlateau(this.getNouvellePosition(joueur), false);
            // false pour le deuxième argument car il recule
        } catch (NomPasValideException e) {
            e.printStackTrace();
        }
    }
    
}
