package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class Start extends Case {
    public static final int RECEIVE_MONEY = 200;
    public Start(String name) {
        super(name);
    }

    public void appliquerEffetCase(Joueur joueur) {
        // rien ne se passe
        // on a ajouté la méthode pour qu'il puisse recevoir l'argent dans la méthode pour se déplacer
    }
}
