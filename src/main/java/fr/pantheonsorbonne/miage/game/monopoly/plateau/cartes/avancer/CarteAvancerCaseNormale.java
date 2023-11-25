package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.avancer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

public class CarteAvancerCaseNormale extends CarteAvancer {

    private String nomCase;

    public CarteAvancerCaseNormale(String description, String nomCase) {
        super(description);
        this.nomCase = nomCase;
    }

    @Override
    public int getNouvellePosition(Joueur joueur) {
    try {
        Plateau plateau = Plateau.getInstance();
        return plateau.getCaseParNom(nomCase); 
    } catch (NomPasValideException e) {
        e.printStackTrace();
        return -1; 
    }
}

}