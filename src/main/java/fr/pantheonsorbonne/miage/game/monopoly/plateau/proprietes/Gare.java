package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

public class Gare extends Propriete {

    private int nombreGaresJoueur = 0;

    public Gare(String name, int price) {
        super(name, price);
    }

    // getteurs
    public int getNombreGaresJoueur() {
        return nombreGaresJoueur;
    }

    @Override
    public int getLoyer() {
        return 25*nombreGaresJoueur;
    }
    
}
