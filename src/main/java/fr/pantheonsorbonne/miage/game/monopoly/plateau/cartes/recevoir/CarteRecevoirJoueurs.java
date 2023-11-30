package fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.recevoir;

import java.util.List;

import fr.pantheonsorbonne.miage.game.monopoly.jeu.JeuLocal;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.PasAssezArgentException;

public class CarteRecevoirJoueurs extends CarteRecevoir {

    private int montant;

    public CarteRecevoirJoueurs(String description, int montant) {
        super(description);
        this.montant = montant;
    }

    List<Joueur> listeJoueurs = JeuLocal.getListeJoueurs();
    int nombreJoueurs = listeJoueurs.size();

    public void joueursDonnentArgent(Joueur joueur) throws PasAssezArgentException {
        // à implémenter : prendre la liste des joueurs et faire qu'ils paient le montant
        for (Joueur j : listeJoueurs) {
            if (!j.equals(joueur)) {
                if (j.getPorteMonnaie() < montant) {
                    throw new PasAssezArgentException(j.getName()+" n'a pas assez d'argent pour payer!");
                }
                j.payer(montant);
            }
        }
    }

    @Override
    protected int montantARecevoir() {
        return montant*(nombreJoueurs - 1); // tous les joueurs sauf celui qui tire la carte vont payer
    }
    
}