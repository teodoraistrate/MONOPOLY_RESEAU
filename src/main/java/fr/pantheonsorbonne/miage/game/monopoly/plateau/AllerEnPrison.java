package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class AllerEnPrison extends Case {

    private Prison prison = Prison.getInstance("Prison");

    public AllerEnPrison(String name) {
        super(name);
    }

    public void appliquerEffetCase(Joueur joueur) {
        prison.mettreJoueurEnPrison(joueur);
        prison.sortirPrisonDoubleDe(joueur);
        if (joueur.choixSortirPrison() && joueur.getPorteMonnaie()>50) {
            prison.sortirPrisonPayer(joueur);
        }
    }
    
}
