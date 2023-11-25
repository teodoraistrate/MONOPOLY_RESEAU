package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class Prison extends Case {

    private HashMap<Joueur,Integer> nombreToursPrison = new HashMap<>();

    protected Prison(String name) {
        super(name);
    }

    public void mettreJoueurEnPrison(Joueur joueur) {
        this.nombreToursPrison.put(joueur, 0);
    }

    public void augmenterNombreTours(Joueur joueur) {
        this.nombreToursPrison.get(joueur).
    }
    
}
