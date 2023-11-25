package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir;

public class CarteRecevoirFixe extends CarteRecevoir {

    private int montant;

    public CarteRecevoirFixe(String description, int montant) {
        super(description);
        this.montant = montant;
    }

    @Override
    protected int montantARecevoir() {
        return montant;
    }
    
    
}
