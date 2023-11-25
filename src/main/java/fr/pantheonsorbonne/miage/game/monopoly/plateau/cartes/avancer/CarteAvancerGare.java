package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.Carte;

public class CarteAvancerGare extends Carte {

    protected CarteAvancerGare(String description) {
        super(description);
    }

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException {
        
    }
    
}
