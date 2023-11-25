package jeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import carte.Carte;

public class Pioche {
    private List<Carte> cartes;

    public Pioche() {
        this.cartes = new ArrayList<>();
    }

    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    public Carte piocherCarte() {
       
        // On mélange les cartes pour simuler le tirage au hasard
        Collections.shuffle(cartes);

        // On renvoie la première carte de la pioche
        return cartes.get(0);
    }

    // Méthode pour mélanger la pioche (optionnelle)
    public void melangerPioche() {
        Collections.shuffle(cartes);
    }

    // public int getNombreCartes() {
    //     return cartes.size();
    // }


}

