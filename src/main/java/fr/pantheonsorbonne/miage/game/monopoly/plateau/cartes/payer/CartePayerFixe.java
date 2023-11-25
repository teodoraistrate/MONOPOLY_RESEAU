package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.payer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class CartePayerFixe extends CartePayer {

    private int montant;

    public CartePayerFixe(String description, int montant) {
        super(description);
        this.montant = montant; 
    }

    @Override
    public int getMontantAPayer(Joueur joueur) {
        return montant;
    }
  
}
