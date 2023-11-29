package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNombre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarteReculerNombreTest {

    @Test
    public void testGetNouvellePosition() {
        int nombreCases = 3;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau(); 
        int nouvellePosition = carte.getNouvellePosition(joueur);
        assertEquals(37, nouvellePosition);
    }

    @Test
    public void testGetNouvellePositionAvecDecalageSupérieur() {
        int nombreCases = 7;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau();

        // Exécution du test
        int nouvellePosition = carte.getNouvellePosition(joueur);

        // Vérification du résultat (doit être 38 cases en arrière)
        assertEquals(33, nouvellePosition);
    }

    @Test
    public void testGetNouvellePositionAvecDecalageSurLePlateau() {
        int nombreCases = 25;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new Joueur(null);
        joueur.getPositionPlateau();
        int nouvellePosition = carte.getNouvellePosition(joueur);

        assertEquals(15, nouvellePosition);
    }
}
