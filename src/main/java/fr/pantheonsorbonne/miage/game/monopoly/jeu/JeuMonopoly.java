package fr.pantheonsorbonne.miage.game.monopoly.jeu;

import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.plateau.Case;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;

public class JeuMonopoly {
    public static void main (String [] args) {

        
        Plateau plateau = Plateau.getInstance();
        List<Case> cases = plateau.getPlateau();
        Case start = cases.get(0);

    }
}
