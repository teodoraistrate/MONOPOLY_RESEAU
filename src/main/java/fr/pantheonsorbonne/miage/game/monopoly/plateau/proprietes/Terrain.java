package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

import java.awt.Color;

public class Terrain extends Propriete {

    private int[] tableauLoyer;
    private int nombreMaisons = 0;
    private boolean estHotel = false;
    private int prixMaison;
    private Color color;

    public Terrain(String name, int price, Color color, int[] tableauLoyer, int prixMaison) {
        super(name, price);
        this.color = color;
        this.tableauLoyer = tableauLoyer;
        this.prixMaison = prixMaison;
    }

    // getteurs

    public int[] getTableauLoyer() {
        return tableauLoyer;
    }

    public int getNombreMaisons() {
        return nombreMaisons;
    }

    @Override
    public int getLoyer() {
        if (this.estHotel())
            return tableauLoyer[5];
        return tableauLoyer[this.getNombreMaisons()];
    }

    public Color getColor() {
        return color;
    }
    
    // is a

    public boolean estHotel() {
        return estHotel;
    }

    // autres methodes

    //créer méthode acheterHotel et acheter maison avec exception dejaAchetéeException

    public void vendreHotel() throws CannotSellException {
        if (!this.estHotel())
            throw new CannotSellException("Vous n'avez pas d'hotel sur ce terrain!");
        this.estHotel = false;
        this.getProprietaire().ajouterArgent(prixMaison / 2);
        this.nombreMaisons = 4;
    }

    public void vendreMaison() throws CannotSellException {
        if (this.getNombreMaisons() == 0)
            throw new CannotSellException("Vous n'avez pas de maison sur ce terrain!");
        this.getProprietaire().ajouterArgent(prixMaison / 2);
        this.nombreMaisons--;
    }

}
