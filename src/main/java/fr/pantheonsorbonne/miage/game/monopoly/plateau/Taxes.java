package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class Taxes extends Case {

    private int montantAPayer;

    public Taxes(String name, int montantAPayer) {
        super(name);
        this.montantAPayer = montantAPayer;
    }

    public int getMontantAPayer() {
        return montantAPayer;
    }

    public void appliquerEffetCase(Joueur joueur) {
        try {
            joueur.payerTaxes(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
