package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;

public class CartePayerOuChance extends Carte {

    protected CartePayerOuChance(String description) {
        super(description);
    }

    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'appliquerEffet'");
    }

    
    
}
