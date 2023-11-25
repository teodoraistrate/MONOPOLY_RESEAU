package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir;

public class CarteRecevoirJoueurs extends CarteRecevoir {

    private int montant;

    protected CarteRecevoirJoueurs(String description, int montant) {
        super(description);
        this.montant = montant;
    }

    @Override
    protected int montantARecevoir() {
        throw new UnsupportedOperationException("Unimplemented method 'montantARecevoir'");
    }

    public void joueursDonnentArgent() {
        // à implémenter : prendre la liste des joueurs et faire qu'ils paient le montant
    }
    
}