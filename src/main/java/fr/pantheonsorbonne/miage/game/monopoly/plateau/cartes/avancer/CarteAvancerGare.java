package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class CarteAvancerGare extends CarteAvancer {

    public CarteAvancerGare(String description) {
        super(description);
    }

    @Override
    public int getNouvellePosition(Joueur joueur) {
        return joueur.getPositionPlateau()/10*10+5;
    }
    
}
