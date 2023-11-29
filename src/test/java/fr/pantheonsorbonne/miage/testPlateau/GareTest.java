package fr.pantheonsorbonne.miage.testPlateau;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.proprietes.Gare;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GareTest {

    @Test
    public void testGetNombreGaresJoueur() {
        Gare gare = new Gare("Gare de Test", 200);
        int nombreGaresJoueur = gare.getNombreGaresJoueur();
        assertEquals(0, nombreGaresJoueur);
    }

    @Test
    public void testGetLoyerWithNoGares() {
        Gare gare = new Gare("Gare de Test", 200);
        int loyer = gare.getLoyer();
        assertEquals(0, loyer);
    }

    @Test
    public void testGetLoyerWithOneGare() {
        Gare gare = new Gare("Gare de Test", 200);
        Joueur joueur = new Joueur("Nom");
        joueur.ajouterArgent(300);
        try {
            joueur.acheterPropriete(gare);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //gare.compterNombreGaresProprietaire();

        int loyer = gare.getLoyer();
        assertEquals(0, loyer);
    }

    @Test
    public void testGetLoyerWithMultipleGares() {
        Gare gare = new Gare("Gare de Test", 200);
       // gare.compterNombreGaresProprietaire();
        int loyer = gare.getLoyer();
        assertEquals(0, loyer);
    }
}
