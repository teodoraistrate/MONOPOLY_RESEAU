package fr.pantheonsorbonne.miage.testCartesReculer;

import fr.pantheonsorbonne.miage.game.monopoly.joueur.Joueur;
import fr.pantheonsorbonne.miage.game.monopoly.joueur.JoueurS1;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.Plateau;
import fr.pantheonsorbonne.miage.game.monopoly.plateau.cartes.reculer.CarteReculerNombre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CarteReculerNombreTest {

    private Plateau plateau;
    private JoueurS1 joueur;

    @BeforeEach
    public void setUp() {
        plateau = Plateau.getInstance();
        joueur = new JoueurS1("TestJoueur");
    }



    @Test
    public void testGetNouvellePosition() {
        int nombreCases = 3;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new JoueurS1("paola");
        
        // on deplace  le joueur à une position spécifique sur le plateau
        joueur.deplacerSurPlateau(20, true);

        int nouvellePosition = carte.getNouvellePosition(joueur);
        assertEquals(17, nouvellePosition); // Si le joueur est à la position 20, il doit aller à 17 (20 - 3)
    }


    @Test
    public void testGetNouvellePositionAvecDecalageSupérieur() {
        int nombreCases = 7;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new JoueurS1(null);
        joueur.getPositionPlateau();
        int nouvellePosition = carte.getNouvellePosition(joueur);

        assertEquals(33, nouvellePosition);
    }

    @Test
    public void testGetNouvellePositionAvecDecalageSurLePlateau() {
        int nombreCases = 25;
        CarteReculerNombre carte = new CarteReculerNombre("Description", nombreCases);

        Joueur joueur = new JoueurS1(null);
        joueur.getPositionPlateau();
        int nouvellePosition = carte.getNouvellePosition(joueur);

        assertEquals(15, nouvellePosition);
    }
}
