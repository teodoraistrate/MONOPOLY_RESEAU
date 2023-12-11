package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.Pioche;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Chance;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.NomPasValideException;

public class CartePayerOuChance extends Carte {

    private int montant;

    public CartePayerOuChance(String description, int montant) {
        super(description);
        this.montant = montant;
    }

    public int getMontantAPayer() {
        return montant;
    }


    @Override
    public void appliquerEffet(Joueur joueur) throws PasAssezArgentException, NomPasValideException {
        if (joueur.choixPayerOuChance(this)) {
            Pioche piocheChance = Chance.getPiocheChance();
            Carte carte = piocheChance.piocherCarte();
            carte.appliquerEffet(joueur);
        } else {
            joueur.payer(montant);
        }
    }

    
    
}
