package fr.pantheonsorbonne.miage.game.monopoly.plateau;

public class Taxes extends Case {

    private int montantAPayer;

    protected Taxes(String name, int montantAPayer) {
        super(name);
        this.montantAPayer = montantAPayer;
    }

    public int getMontantAPayer() {
        return montantAPayer;
    }
    
}
