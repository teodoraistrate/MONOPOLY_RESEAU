package fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes;

public class Compagnie extends Propriete {

    public Compagnie(String name, int price) {
        super(name, price);
    }

    @Override
    public int getLoyer() {
        return 75;
    }
    
}

