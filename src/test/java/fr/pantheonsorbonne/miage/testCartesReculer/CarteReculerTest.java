package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteReculerTest {

    @Test
    public void testAppliquerEffet() {
        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau(); // Position arbitraire pour le test

        joueur.appliquerEffetCase(joueur);

        // Vérification du résultat (doit être 2 cases en arrière)
        assertEquals(2, joueur.getPositionPlateau());
    }

    @Test
    public void testAppliquerEffetAvecDecalageSupérieur() {
        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau();

        joueur.appliquerEffetCase(joueur);

        // Vérification du résultat (doit être 38 cases en arrière)
        assertEquals(38, joueur.getPositionPlateau());
    }

    @Test
    public void testAppliquerEffetAvecDecalageSurLePlateau() {
        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau();

        joueur.appliquerEffetCase(joueur);

        // Vérification du résultat (doit être 5 cases en arrière)
        assertEquals(5, joueur.getPositionPlateau());
    }
}
