package fr.pantheonsorbonne.miage.game.monopoly.plateau;

import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.DeDouble;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;

public class Prison extends Case {

    private static Prison instance = new Prison("Prison");
    private HashMap<Joueur,Integer> nombreToursPrison = new HashMap<>();
    public static final int MONTANT_SORTIR = 50;

    public Prison(String name) {
        super(name);
    }

    public static Prison getInstance(String nom) {
        if (instance == null) {
            instance = new Prison(nom);
        }
        return instance;
    }

    public int getNombreToursPrison(Joueur joueur) {
        return nombreToursPrison.get(joueur);
    }

    public HashMap<Joueur,Integer> getListeNbTours() {
        return nombreToursPrison;
    }

    public void mettreJoueurEnPrison(Joueur joueur) {
        nombreToursPrison.put(joueur, 0);
        joueur.mettreEnPrison();
    }

    public void augmenterNombreTours(Joueur joueur) {
        nombreToursPrison.put(joueur, nombreToursPrison.get(joueur)+1);
        if (this.nombreToursPrison.get(joueur) >= 3) {
            this.sortirPrison(joueur);
        }
    }

    public void sortirPrison(Joueur joueur) {
        this.nombreToursPrison.remove(joueur);
        joueur.sortirDePrison();
    }

    public void sortirPrisonDoubleDe(Joueur joueur) {
        DeDouble de = new DeDouble();
        if (de.memeValeur()) {
            this.sortirPrison(joueur);
        }
        System.out.println(joueur.getName() + " est sorti de la prison grâce à un Double Dé!");
    }
    
    public void sortirPrisonPayer(Joueur joueur) {
        try {
            joueur.payer(MONTANT_SORTIR);
            this.sortirPrison(joueur);
            System.out.println(joueur.getName() + " a payé pour sortir de la prison!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ajouter méthode pour qu'il puisse payer pour sortir

    public void appliquerEffetCase(Joueur joueur) {
        // rien ne se passe, c'est une simple visite
        // un info logger
    }
    
}
